package it.amrito.business;

import it.amrito.model.CsvInfo;
import it.amrito.model.Product;
import it.amrito.utils.SingletonSessionFactory;
import it.amrito.validation.ProductValidator;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.Scanner;

public class CsvReaderBo {
    private static final Logger logger = LoggerFactory.getLogger(CsvReaderBo.class);


    private static CsvReaderBo instance;

    private CsvReaderBo(){

    }

    public static synchronized CsvReaderBo getInstance(){
        if(instance == null) instance = new CsvReaderBo();

        return instance;
    }


    public String readPathFromCommandLine(){
        Scanner console = new Scanner(System.in);
        System.out.println("inserire path al file.csv");
        return console.next();
    }


    public boolean saveToDb(CsvInfo csvInfo){
        SessionFactory factory = SingletonSessionFactory.getInstance();
        Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(csvInfo);
            transaction.commit();
        }
        catch (HibernateException e){
            logger.error("Errore during saving process, error: {}, message: {}, cause: {}",
                    e.getClass(), e.getMessage(), e.getCause());
            if(transaction != null) transaction.rollback();
            return false;
        }finally {
            session.close();
        }
        return true;
    }

    public CsvInfo convertToEntity(String path){
        File file = new File(path);
        String fileName = file.getName();
        long bytesDimension = file.length();
        CsvInfo csvInfo = new CsvInfo(fileName, bytesDimension);
        int lineCounter = 0;
        try (BufferedReader br= Files.newBufferedReader(file.toPath());){
            while (br.ready()){
                lineCounter ++;
                String[] line = br.readLine().split(";");
                if(lineCounter == 1) continue;
                ProductValidator.validateProduct(line, lineCounter);
                Product product = new Product(Long.valueOf(line[0]), line[1], LocalDate.parse(line[2]), Double.valueOf(line[3]));
                csvInfo.getProductSet().add(product);
                product.setCsvInfo(csvInfo);
            }
        }
        catch(IOException e){
            logger.error("ERROR during the process of file reading, exception: {}, Message: {}", e.getClass(), e.getMessage());
            System.exit(-1);
        }
        return csvInfo;
    }


}
