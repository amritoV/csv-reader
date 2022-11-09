package it.amrito.validation;

import it.amrito.exceptions.InvalidDataException;
import it.amrito.utils.Constants;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.function.Predicate;

public class ProductValidator {


    public static void validateProduct(String[] line, int lineCounter) throws InvalidDataException {
        Optional<Integer> columnErrorOpt = Optional.empty();
        if(line[0] == null || line[0].isBlank())
            columnErrorOpt = Optional.of(1);
        if(line[1] == null || line[1].isBlank())
            columnErrorOpt = Optional.of(2);

        Predicate<String> isValidDate = x -> {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT_YYYYMMDD_WITHDASH);
            try {
                LocalDate.parse(x, dateFormatter);
            }catch (DateTimeParseException e){
                return false;
            }
            return true;
        };

        if(line[2] == null || line[2].isBlank() || !isValidDate.test(line[2]))
            columnErrorOpt = Optional.of(3);
        if(line[3] == null || line[3].isBlank())
            columnErrorOpt = Optional.of(4);

        if(columnErrorOpt.isPresent()) throw new InvalidDataException("error during validation of data", lineCounter, columnErrorOpt.get());
    }
}
