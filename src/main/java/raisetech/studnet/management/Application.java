package raisetech.studnet.management;

import io.micrometer.common.util.StringUtils;
import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

	private  String name="kei kaki";
	private String age ="36";

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@GetMapping("/studentInfo")
	public String getName(){
		return name+" "+age+"歳";
	}

	@PostMapping("/studentInfo")
	public void studentInfo(String name,String age){
		this.name = name;
		this.age  = age;
	}

	@PostMapping("/studentName")
	public void updateStudentName(String name){
		this.name = name;
	}


}
