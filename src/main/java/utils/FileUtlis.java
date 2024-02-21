package utils;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtlis {

    // remove all the special characters
    public String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }

    // convert to CSV format
    public String convertToCSV(String[] data) {
        return Stream.of(data)
                .map(this::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    // convert to Pipe Delimiter format
    public String convertToPipeDelimiter(String[] data) {
        return (String) Stream.of(data)
                .map(this::escapeSpecialCharacters)
                .collect(Collectors.joining("|"));
    }

    /**
     * @param dataLines   source of data to be written in file with
     * @param destination file output destination
     */
    public void createCSVFormatFile(List<String[]> dataLines, String destination) throws IOException {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(destination), Charset.forName("UTF_16LE"));
        try (PrintWriter pw = new PrintWriter(outputStreamWriter)) {
            dataLines.stream()
                    .map(this::convertToCSV)
                    .forEach(pw::println);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    /**
     * Avoid issue https://en.wikipedia.org/wiki/Byte_order_mark when generating the CSV file.
     * @param dataLines   source of data to be written in file with
     * @param destination file output destination
     */
    public void createCSVFileByOpenCSV(List<String[]> dataLines, String destination) {
        try {
            FileWriter outputFile = new FileWriter(destination);
            CSVWriter writer = new CSVWriter(outputFile,
                    CSVWriter.DEFAULT_SEPARATOR,
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
            writer.writeAll(dataLines);
            writer.close();
        } catch (IOException e) {
            System.err.println(e.getStackTrace());
        }
    }

    /**
     * @param dataLines   source of data to be written in file with
     * @param destination file output destination
     */
    public void createPipeDelimiterFormatFile(List<String[]> dataLines, String destination) throws IOException {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(destination));

        try (PrintWriter pw = new PrintWriter(outputStreamWriter)) {
            dataLines.stream()
                    .map(this::convertToPipeDelimiter)
                    .forEach(pw::println);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    /**
     * Write file to a common folder
     *
     * @param fileName     file name to be created.
     * @param responseBody source data of the file.
     */
    // Write the data into a file
    public void writeFile(String fileName, String responseBody, String outputPath) {
        try {
            Files.write(Paths.get(outputPath, fileName), responseBody.getBytes());
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }

    /**
     * Read the latest file in Download folder
     *
     * @return
     */
    public File getLastedModifiedFile() {
        String dirPath = System.getProperty("setelWeb.downloadPath");
        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            return null;
        }

        File lastModifiedFile = files[0];
        for (int i = 1; i < files.length; i++) {
            if (lastModifiedFile.lastModified() < files[i].lastModified()) {
                lastModifiedFile = files[i];
            }
        }
        return lastModifiedFile;
    }

    /**
     * Read the latest download file
     *
     * @param c:   class of the csv class
     * @param <T>: csv class
     * @return
     */
    public <T> List<T> readLatestCSVFileDownloaded(Class<T> c) {
        File file = getLastedModifiedFile();
        Reader reader = null;
        try {
            reader = Files.newBufferedReader(file.toPath());
        } catch (IOException ex) {
            System.err.println(ex.getMessage() + "\n");
        }
        CsvToBean<T> csvToBean = new CsvToBeanBuilder(reader)
                .withType(c)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
        List<T> csvContent = csvToBean.parse();
        return csvContent;
    }

    /**
     * Read csv file from path
     *
     * @param c:    class of the csv class
     * @param <T>:  csv class
     * @param path: path to csv file
     * @return
     */
    public <T> List<T> readCSVFileFromPath(Class<T> c, String path) {
        Reader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get(path));
        } catch (IOException ex) {
            System.err.println(ex.getMessage() + "\n");
        }
        CsvToBean<T> csvToBean = new CsvToBeanBuilder(reader)
                .withType(c)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
        List<T> csvContent = csvToBean.parse();
        return csvContent;
    }

    //Read file
    public String readFile(String filePath) throws Exception {
        File file = new File(filePath);
        return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
    }

    /**
     * Read csv file from string
     *
     * @param c:         class of the csv class
     * @param <T>:       csv class
     * @param csvString: the csv in the format of a whole string
     * @return
     */
    public <T> List<T> readCSVFromString(Class<T> c, String csvString) {
        StringReader stringReader = new StringReader(csvString);
        CSVReader csvReader = new CSVReaderBuilder(stringReader).build();
        return new CsvToBeanBuilder(csvReader)
                .withType(c)
                .withIgnoreLeadingWhiteSpace(true)
                .build().parse();
    }

    /**
     * Get list files in folder
     *
     * @param folder: folder contains files
     * @return
     */
    public static List<String> listFilesForFolder(final File folder) {
        List<String> lineTXT = new ArrayList<String>();
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                lineTXT.add(fileEntry.getName());
            }
        }
        return lineTXT;
    }

    /**
     * read file *.txt
     *
     * @param filePath: path of file
     * @return
     */
    public static List<String> readFileTXT(String filePath)  {
        List<String> eventNames = new ArrayList<>();
        File file = new File(filePath);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String st = null;
        while (true) {
            try {
                if (!((st = br.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            eventNames.add(st);
        }
        return eventNames;
    }

    /**
     * Get lines in csv file
     *
     * @param fileName: file
     * @return
     */
    public static List<String> getLinesInCSVFile(String fileName) throws IOException {
        List<String> lines = new ArrayList<String>();
        LineIterator it = null;
        try {
            it = FileUtils.lineIterator(new File(fileName), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            while (it.hasNext()) {
                String line = it.nextLine();
                lines.add(line);
            }
        } finally {
            it.close();
        }

        return lines;
    }

    /**
     * Read csv file from path
     *
     * @param c:    class of the csv class
     * @param <T>:  csv class
     * @param path: path to csv file
     * @param numberLine: number of lines to skip
     * @return
     */
    public <T> List<T> readCSVFileSkipLineFromPath(Class<T> c, String path, int numberLine) {
        Reader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get(path));
        } catch (IOException ex) {
            System.err.println(ex.getMessage() + "\n");
        }
        CsvToBean<T> csvToBean = new CsvToBeanBuilder(reader)
                .withType(c)
                .withIgnoreLeadingWhiteSpace(true)
                .withSkipLines(numberLine)
                .build();
        List<T> csvContent = csvToBean.parse();
        return csvContent;
    }

    /**
     * Reads the content from a given yml file and transforms it into a HashMap
     * Make sure the yml file content is properly structured
     *
     * @param file is the path to yml file to read from
     * @return HashMap data
     */
    public static HashMap readYAMLValuesAsHashMap(String file) {
        Yaml yaml = new Yaml();
        InputStream input = null;
        HashMap result = null;
        try {
            input = FileUtlis.class.getResourceAsStream(file);
            result = (HashMap) yaml.load(input);

            if (input != null) {
                input.close();
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage() + "\n");
        }
        return result;
    }

    /**
     * Read latest csv file from path
     * @param fileName: name of csv file
     * @return
     */
    public void removeDownloadedFileWithFileName(String fileName) {
        String downloadPath = System.getProperty("setelWeb.downloadPath");
        File dir = new File(downloadPath);
        File[] dirContents = dir.listFiles();
        for (File f : dirContents) {
            if (f.getName().startsWith(fileName)) {
                f.delete();
            }
        }
    }
}

