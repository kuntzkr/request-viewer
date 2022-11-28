package com.krute.request.viewer.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ViewerController {

    @GetMapping("/request-details")
    public Map<String, String> getRequestDetails(HttpServletRequest request) {
        Map<String, String> reqDetails = new HashMap<String, String>();
        StringBuilder sb = new StringBuilder("GET Request Details\n");

        // HTTP Method
        reqDetails.put("HTTP Method", request.getMethod());

        // URI
        reqDetails.put("URI", request.getRequestURI());

        // Headers
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String nextHeader = (String) headerNames.nextElement();
            reqDetails.put(" **HEADER** " + nextHeader, request.getHeader(nextHeader));
        }

        // Params
        Enumeration params = request.getParameterNames();
        while (params.hasMoreElements()) {
            String paramName = (String) params.nextElement();
            reqDetails.put(" **PARAM** " + paramName, request.getParameter(paramName));
        }

        // Get request has no body

        return reqDetails;
    }

    @PostMapping("/request-details")
    public Map<String, String> getPostRequestDetails(HttpServletRequest request) throws IOException {
        Map<String, String> reqDetails = new HashMap<String, String>();
        StringBuilder sb = new StringBuilder("GET Request Details\n");

        // HTTP Method
        reqDetails.put("HTTP Method", request.getMethod());

        // URI
        reqDetails.put("URI", request.getRequestURI());

        // Headers
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String nextHeader = (String) headerNames.nextElement();
            reqDetails.put(" **HEADER** " + nextHeader, request.getHeader(nextHeader));
        }

        // Params
        Enumeration params = request.getParameterNames();
        while (params.hasMoreElements()) {
            String paramName = (String) params.nextElement();
            reqDetails.put(" **PARAM** " + paramName, request.getParameter(paramName));
        }

        // Get the POST request body
        Scanner s = new Scanner(request.getInputStream(), "UTF-8").useDelimiter("\\A");
        reqDetails.put("POST Request Body", s.hasNext() ? s.next() : "");
        s.close();

        return reqDetails;
    }
}
