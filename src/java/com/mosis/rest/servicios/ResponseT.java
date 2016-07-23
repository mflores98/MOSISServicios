/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mosis.rest.servicios;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Owner
 */
public class ResponseT<T> {

    @Getter @Setter public int code;
    @SerializedName(value = "data")
    @Getter @Setter public T results;
    @Getter @Setter private String message;

}