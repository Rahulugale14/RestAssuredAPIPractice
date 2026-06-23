package day6;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import day2.Pojo_PostRequest;
import org.testng.annotations.Test;

// POJO --> Serialize --> JSON Object --> De-serialize --> POJO
public class SerializationDeserialization {

    //@Test
    void convertPOJO2JSON() throws JsonProcessingException {

        //created JAVA object using POJO Class
        Student stuPojo = new Student();            //pojo
        stuPojo.setName("Rahul");
        stuPojo.setLocation("Pune");
        stuPojo.setPhone("797291528");
        stuPojo.setCourses(new String[]{"C", "C++"});

        //convert JAVA object ---> JSON object (serialization)
        ObjectMapper mapper = new ObjectMapper();
        String jsonData = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(stuPojo);
        System.out.println(jsonData);

    }


    //JSON Object --> De-serialize
    @Test
    void convertJSON2POJO() throws JsonProcessingException {

        String jsonData = "{\n" +
                "  \"name\" : \"Rahul\",\n" +
                "  \"location\" : \"Pune\",\n" +
                "  \"phone\" : \"797291528\",\n" +
                "  \"courses\" : [ \"C\", \"C++\" ]\n" +
                "}";

        //convert JSON data ---> POJO object (De-serialization)
        ObjectMapper mapper = new ObjectMapper();
        Student stuPojo = mapper.readValue(jsonData, Student.class);
        System.out.println("Name: " + stuPojo.getName());
        System.out.println("Location: " + stuPojo.getLocation());
        System.out.println("Phone: " + stuPojo.getPhone());
        System.out.println("Courses: " + stuPojo.getCourses()[0]);
        System.out.println("Courses: " + stuPojo.getCourses()[1]);


    }

}
