package it.amrito;

import it.amrito.business.CsvReaderBo;
import it.amrito.model.CsvInfo;
import it.amrito.utils.StopWatch2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    private static final String RELATIVE_PATH = "src/main/resources/csvexamples/file.csv";
    private static final String ABSOLUTE_PATH = "/home/amrit/IdeaProjects/csv-reader/src/main/resources/csvexamples/file.csv";


    public static void main(String[] args) {
        logger.debug("starting process");
        CsvReaderBo csvReaderBo = CsvReaderBo.getInstance();
        String path = csvReaderBo.readPathFromCommandLine();
        StopWatch2 sp2 = new StopWatch2();
        logger.info("path to file is: {}. Starting conversion into entity model", path);
        CsvInfo csvInfo = csvReaderBo.convertToEntity(path);
        logger.info("finished conversion into entity model. Saving into DB number of products : {}. ", csvInfo.getProductSet().size());
        boolean result= csvReaderBo.saveToDb(csvInfo);
        if(!result) {
            logger.error("Error while saving into db, transaction not commited. ms: {}", sp2.getTotalTimeInMillis());
            return;
        }
        logger.info("finished process execution, saved into database number of elements: {} in ms: {}",
                csvInfo.getProductSet().size(), sp2.getTotalTimeInMillis());
    }

}