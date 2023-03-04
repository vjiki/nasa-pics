package com.github.vjiki;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.vjiki.client.NasaHttpClient;
import com.github.vjiki.constants.NasaConstants;
import com.github.vjiki.model.request.PhotosRequest;
import com.github.vjiki.model.response.PhotosResponse;
import com.github.vjiki.service.DateGenerator;
import com.github.vjiki.service.PhotoProcessor;
import org.json.JSONObject;

import javax.swing.text.DateFormatter;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.github.vjiki.constants.NasaConstants.TARGET_URL_API_KEY;
import static com.github.vjiki.constants.NasaConstants.TARGET_URL_CAMERA_NAME;

public class Main {

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {

        NasaHttpClient nasaHttpClient = new NasaHttpClient();
        PhotoProcessor photoProcessor = new PhotoProcessor();
        DateGenerator dateGenerator = new DateGenerator();
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> daysList = new ArrayList<>();
        //JSONObject output = new JSONObject();

        HashMap<String, List<String>> output = new HashMap<>();

//        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
//        System.out.println(json);



        PhotosRequest photosRequest = objectMapper.readValue(new File("src/main/resources/photosApi.json"), PhotosRequest.class);

        dateGenerator.getLastDaysFromNow(daysList,photosRequest.getDays()-1);
//        DateTimeFormatter d = DateTimeFormatter.ofPattern("yyyy-M-d");
        //Comparator<MyData> comparator = Comparator.comparing(LocalDate.parse(, d));



//        daysList.sort(Comparator.comparing(e->  LocalDate.parse(e, d)));
//        daysList.sort(Collections.reverseOrder());
//
//        List<String> set = daysList.stream()
//                .sorted(Comparator.comparing(e -> LocalDate.parse(e, d))).sorted(Collections.reverseOrder()).toList();

        //daysList.sort(Collections.reverseOrder());

        //List<PhotosResponse> photosResponseList = new ArrayList<>();

        for (String day: daysList) {
            System.out.println(day);
            String targetUrl  = String.format(NasaConstants.TARGET_URL +
                            "photos?earth_date=%s&camera=%s&api_key=%s",
                    day,TARGET_URL_CAMERA_NAME,TARGET_URL_API_KEY);

            HttpResponse<String> response = nasaHttpClient.fetchPhotos(targetUrl);

            // Reading response
            //Todo[] todos = objectMapper.readValue(response.body(), Todo[].class);
            PhotosResponse photosResponse = objectMapper.readValue(response.body(), PhotosResponse.class);

            //System.out.println("total photos : "+ photosResponse.getPhotos().size());

            //System.out.println(photosResponse);

            photoProcessor.process(day, output, photosRequest, photosResponse);

            //photosResponseList.add(photosResponse);
        }


        // Sort map in Java 8 and above by reverse ordering of its keys
        Map<String, List<String>> reverseSortedMap = new LinkedHashMap<>();

        DateTimeFormatter d = DateTimeFormatter.ofPattern("yyyy-M-d");
//        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-M-d");

        output.entrySet()                   // Set<Entry<String, String>>
                .stream()                   // Stream<Entry<String, String>>
                .sorted(Comparator.comparing(e->  LocalDate.parse(e.getKey(), d)))
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .forEachOrdered(entry ->
                        reverseSortedMap.put(entry.getKey(), entry.getValue()));


        //        ObjectMapper mapper = new ObjectMapper();
        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(reverseSortedMap);
        System.out.println(json);

//        String a = "asa";
//        String a1 = "asa";
//        String a2 = new String("asa");
//        System.out.println(a.intern() == a1.intern());
//        System.out.println(a.intern() == a2.intern());
//        System.out.println(a == a1);
//        System.out.println(a == a2);
//        a += " sasas";
//        a = a + " asas";

//        System.out.println(a);
//        System.out.println(a.intern());
        //System.out.println(output);


        //Arrays.stream(todos).peek(e -> System.out.println("Todo value: " + e)).collect(Collectors.toList());

        //System.out.println("total tasks : "+todos.length);


//        long count = Arrays.stream(todos).filter(Todo::isCompleted).count();
//        System.out.println("Total completed tasks : " + count);

//        List<Integer> completedTaskIds = Arrays.stream(todos).filter(Todo::isCompleted).map(Todo::getId).toList();
//        completedTaskIds.forEach(System.out::println);
    }
}