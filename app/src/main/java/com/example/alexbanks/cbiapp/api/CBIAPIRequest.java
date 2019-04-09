package com.example.alexbanks.cbiapp.api;

import org.json.JSONObject;

import java.net.URL;

public class CBIAPIRequest {

    URL apiLocation;
    JSONObject requestObject;

    JSONObject responseObject;
    int responseCode;

    CBIAPIRequestResponseHandler responseHandler;

}
