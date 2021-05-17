package com.better.modules.base.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * ShiroIntegration
 */
@Data
@ToString
public class User implements Serializable {

    private static final long serialVersionUID = -6056125703075132981L;

    private Integer id;

    private String account;

    private String password;

    private String username;
}
