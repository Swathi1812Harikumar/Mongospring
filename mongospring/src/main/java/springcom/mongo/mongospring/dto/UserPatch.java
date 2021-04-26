package springcom.mongo.mongospring.dto;

import lombok.*;

//public class UserPatch {
//}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserPatch {
    private String action;
    private String fieldName;
    private String value;
}
