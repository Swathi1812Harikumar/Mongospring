package springcom.mongo.mongospring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Document
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    //@GeneratedValue(strategy= GenerationType.AUTO)
    private String id;
    private String name;
    private String email;

}
