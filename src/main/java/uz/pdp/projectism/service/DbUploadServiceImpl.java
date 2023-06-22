package uz.pdp.projectism.service;

import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.projectism.entity.Ism;
import uz.pdp.projectism.entity.User;
import uz.pdp.projectism.payload.ApiResponse;
import uz.pdp.projectism.repository.IsmRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DbUploadServiceImpl implements DbUploadService {
    private final IsmRepository ismRepository;

    @Override
    public ApiResponse<?> uploadData(MultipartFile file) throws IOException {
        return null;
    }


//    @Override
//    public ApiResponse<?> uploadData(MultipartFile file) throws IOException {
//        try (BufferedReader fileReader=new BufferedReader(new InputStreamReader(file,"UTS-8"))) {
//
//        }
//        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//             CSVParser csvParser = new CSVParser(fileReader,
//                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
//
//            List<Tutorial> tutorials = new ArrayList<Tutorial>();
//
//            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
//
//            for (CSVRecord csvRecord : csvRecords) {
//                Tutorial tutorial = new Tutorial(
//                        Long.parseLong(csvRecord.get("Id")),
//                        csvRecord.get("Title"),
//                        csvRecord.get("Description"),
//                        Boolean.parseBoolean(csvRecord.get("Published"))
//                );
//
//                tutorials.add(tutorial);
//            }
//
//            return tutorials;
//        } catch (IOException e) {
//            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
//        }
//        return null;

//    }

//    @Override
//    public ApiResponse<?> uploadData(MultipartFile file) throws IOException {
//        List<Ism> ismList=new ArrayList<>();
//        InputStream inputStream = file.getInputStream();
//        CsvParserSettings setting = new CsvParserSettings();
//        setting.setHeaderExtractionEnabled(true);
//        CsvParser csvParser =new CsvParser(setting);
//
////        CSVParser csvParser = new CSVParserBuilder()
////                .withSeparator(',')
////                .withIgnoreQuotations(true)
////                .build();
//
//        csvParser.parseLine("");
//
//        List<Record> parseAllRecords = csvParser.parseAllRecords(inputStream);
//        parseAllRecords.forEach(
//                record -> {
//                    Ism ism = new Ism();
//                    ism.setNameLat(record.getString("nameLat"));
//                    ism.setNameCyr(record.getString("nameCry"));
//                    ism.setGender(record.getBoolean("gender"));
//                    ism.setDefinition(record.getString("definition"));
//                    ism.setNamedPeople(record.getString("namedPeople"));
//                    ism.setLikeCount(record.getInt("likeCount"));
//                    ism.setComingLang(record.getString("comingLang"));
//                    ismRepository.save(ism);
////                    ismList.add(ism);
//
//                }
//        );
//        ismRepository.saveAll(ismList);
//        return ApiResponse.successResponse("Saved Successfully");
//    }
}
