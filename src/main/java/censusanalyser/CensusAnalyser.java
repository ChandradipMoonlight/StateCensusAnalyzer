package censusanalyser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class CensusAnalyser {
    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            CsvToBeanBuilder<IndiaCensusCSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(IndiaCensusCSV.class);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<IndiaCensusCSV> csvToBean = csvToBeanBuilder.build();
            Iterator<IndiaCensusCSV> censusCSVIterator = csvToBean.iterator();;
            Iterable<IndiaCensusCSV> csvIterable = () -> censusCSVIterator;
            int numOfEateries = (int) StreamSupport.stream(csvIterable.spliterator(),false).count();
            return numOfEateries;
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                                              CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (IllegalStateException e) {
            throw new CensusAnalyserException(e.getMessage(),
                                             CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException("Invalid header or Delimiter",
                                             CensusAnalyserException.ExceptionType.INVALID_HEADER_AND_DELIMITER);
        }
    }

    public int loadIndianStateCodeData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            CsvToBeanBuilder<IndianStateCodeCSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(IndianStateCodeCSV.class);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<IndianStateCodeCSV> csvToBean = csvToBeanBuilder.build();
            Iterator<IndianStateCodeCSV> stateCodeCSVIterator = csvToBean.iterator();;
            Iterable<IndianStateCodeCSV> csvIterable = () -> stateCodeCSVIterator;
            int numOfEateries = (int) StreamSupport.stream(csvIterable.spliterator(),false).count();
            return numOfEateries;
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                                              CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (IllegalStateException e) {
            throw new CensusAnalyserException(e.getMessage(),
                                             CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException("Invalid header or Delimiter",
                                            CensusAnalyserException.ExceptionType.INVALID_HEADER_AND_DELIMITER);
        }
    }
}
