package com.sailfish.authorization.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * User entity
 *
 * @author XIAXINYU3
 * @date 2019.7.3
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private String roles;
}
