package model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class User implements Serializable {
    private static final long serialVersionUID = 6195354622397742664L;
    private Integer id;
    private String name;
    private String password;
    //user,admin,president
    private String role;
    private String permissions;
    private String phone;
}
