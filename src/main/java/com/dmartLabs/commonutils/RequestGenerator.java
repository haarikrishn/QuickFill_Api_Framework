package com.dmartLabs.commonutils;

import com.dmartLabs.config.CommonConstants;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static io.restassured.RestAssured.given;

/**
 * @author
 *
 */
public class RequestGenerator implements CommonConstants {
	static String sourceClass = RequestGenerator.class.getName();
	static Logger LOGGER = Logger.getLogger(sourceClass);
	static RequestSpecification requestSpecification;



	//--------------------------------------------------------------------------------------------------------
	//role based token //access token
	//--------
	/**
	 * @param header
	 * @return request
	 */
	public static RequestSpecification getRequest(Map<String, String> header) {
		try {
			// LOGGER.info("Path " + path);
			LOGGER.info("header " + header);
			requestSpecification = given().contentType(ContentType.JSON).baseUri(BASE_URL).headers(header);
			printRequestLogInReport(requestSpecification);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestSpecification;
	}
	//-----------------------------------------------------------------------------
	//create task// update task
//	--------------
	/**
	 * @param header
	 * @param request
	 * @return request
	 */
	public static RequestSpecification getRequest(Map<String, String> header, Object request) {
		try {
			// LOGGER.info("Path " + path);
			LOGGER.info("header " + header);
			requestSpecification = given().contentType(ContentType.JSON).baseUri(BASE_URL).headers(header)
					.body(request);
			printRequestLogInReport(requestSpecification);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestSpecification;
	}
//--------------------------------------------------------------------------------------------
	/**
	 * @author
	 * @param request
	 * @return RequestSpecification
	 */



	//update floorwalk
	//-------------------
public static RequestSpecification getRequestMap(Map<String,String> header,Map<String,String> request) {
	try {
		// LOGGER.info("Path " + path);
		LOGGER.info("request " + request);
		requestSpecification = given().contentType(ContentType.JSON).baseUri(BASE_URL).headers(header).body(request);
		printRequestLogInReport(requestSpecification);
	} catch (Exception e) {
		e.printStackTrace();
	}
	LOGGER.info("requestSpecification : " + requestSpecification);
	return requestSpecification;
}
//-------------------------------------------------------------------------------------------
	//get all summary
	//--------------
public static RequestSpecification getRequestByMap(Map<String, String> header, Map<String, Integer> body) {
	try {
		// LOGGER.info("Path " + path);
		LOGGER.info("header " + header);
		requestSpecification = given().contentType(ContentType.JSON).body(body).baseUri(BASE_URL).headers(header);
		printRequestLogInReport(requestSpecification);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return requestSpecification;
}
//================================================================================================================

	public static RequestSpecification getRequestGet(Map<String, String> header) {
		try {
			// LOGGER.info("Path " + path);
			LOGGER.info("header " + header);
			requestSpecification = given().contentType(ContentType.JSON).baseUri(BASE_URL).headers(header);
			printRequestLogInReport(requestSpecification);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestSpecification;
	}










	//====================================================================================================
	public static RequestSpecification getRequestByMapgetAll(Map<String, String> header, Map<Object,Object> body) {
		try {
			// LOGGER.info("Path " + path);
			LOGGER.info("header " + header);
			requestSpecification = given().contentType(ContentType.JSON).body(body).baseUri(BASE_URL).headers(header);
			printRequestLogInReport(requestSpecification);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestSpecification;
	}

	//-------------------------------------------------------------------------------------------
	//without beaarer token

	/**
	 * @param request
	 * @return
	 */
	public static RequestSpecification getRequest(Object request) {
		try {
			// LOGGER.info("Path " + path);
			LOGGER.info("request " + request.toString());
			requestSpecification = given().contentType(ContentType.JSON).baseUri(BASE_URL).body(request);
			printRequestLogInReport(requestSpecification);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("requestSpecification : " + requestSpecification);
		return requestSpecification;
	}
	//---------------------------------------------------------------------------------------------------

	public static RequestSpecification GetRequestWithQueryParamString(Map<String,String>header,Map<String, String> queryParam) {
		try {
			//LOGGER.info("Path " + path);
			LOGGER.info("queryParam " + queryParam);
			requestSpecification = given().contentType(ContentType.JSON).baseUri(BASE_URL).headers(header)
					.queryParams(queryParam);
			printRequestLogInReport(requestSpecification);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestSpecification;
	}







	//------------------------------------------------------------------
	public static RequestSpecification getRequestFilepath(Map<String,String> header,File jsonfileObject) {
	//	System.out.println("path of URL " + path);
		try {
			requestSpecification = given().contentType(ContentType.JSON).headers(header).baseUri(BASE_URL).body( jsonfileObject);
			printRequestLogInReport(requestSpecification);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestSpecification;
	}
//json file to string
	public static RequestSpecification getRequestFileToString(Map<String,String> header,String jsonfileObject) {
		//	System.out.println("path of URL " + path);
		try {
			requestSpecification = given().contentType(ContentType.JSON).headers(header).baseUri(BASE_URL).body( jsonfileObject);
			printRequestLogInReport(requestSpecification);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestSpecification;
	}

//--------------------------------------------------------------------------

	public static RequestSpecification getRequest(Map<String,String> header,File file ) {
//		System.out.println("path of URL " + path);
		try {
			requestSpecification = given().contentType(ContentType.JSON).headers(header).baseUri(BASE_URL).body(file);
			printRequestLogInReport(requestSpecification);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestSpecification;
	}

	//------------------------------------------------------------------------------------------------

	//using json file to post a request
	public  static  void getRequestPayLoadFromJsonFile(String jsonFileName)
	{

		File jsonfile = new File(jsonFileName);



	}


	//----------------------------------------------------------------------------------------------------------
	public static RequestSpecification getRequest(File file ) {
//		System.out.println("path of URL " + path);
		try {
			requestSpecification = given().contentType(ContentType.JSON).baseUri(BASE_URL).body(file);
			printRequestLogInReport(requestSpecification);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestSpecification;
	}


//----------------------------------------------------------------


	/**
	 * @author
	 * @param path
	 * @return RequestSpecification
	 */
	public static RequestSpecification getRequest(String path) {
		System.out.println("path of URL " + path);
		try {
			requestSpecification = given().contentType(ContentType.JSON).baseUri(BASE_URL + path);
			printRequestLogInReport(requestSpecification);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestSpecification;
	}




	/**
	 *
	 * @param path
	 * @return
	 */
	public static RequestSpecification getRequestWithPathAndBody(String path,Object requestBody) {
		System.out.println("path of URL " + path);
		try {
			requestSpecification = given().contentType(ContentType.JSON).baseUri(BASE_URL + path).body(requestBody);
			printRequestLogInReport(requestSpecification);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestSpecification;
	}






	/**
	 * @author
	 * @param request
	 * @return RequestSpecification
	 */
	public static RequestSpecification getRequest(HashMap<String,String> request) {
		try {
			// LOGGER.info("Path " + path);
			LOGGER.info("request " + request);
			requestSpecification = given().contentType(ContentType.JSON).baseUri(BASE_URL).body(request);
			printRequestLogInReport(requestSpecification);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("requestSpecification : " + requestSpecification);
		return requestSpecification;
	}


    public static RequestSpecification getRequestMap(Map<String,String> request) {
        try {
            // LOGGER.info("Path " + path);
            LOGGER.info("request " + request);
            requestSpecification = given().contentType(ContentType.JSON).baseUri(BASE_URL).body(request);
            printRequestLogInReport(requestSpecification);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("requestSpecification : " + requestSpecification);
        return requestSpecification;
    }



	public static RequestSpecification getRequestMap(Map<String,String> header, ArrayList<Object> request) {
		try {
			// LOGGER.info("Path " + path);
			LOGGER.info("request " + request);
			requestSpecification = given().contentType(ContentType.JSON).baseUri(BASE_URL).headers(header).body(request);
			printRequestLogInReport(requestSpecification);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("requestSpecification : " + requestSpecification);
		return requestSpecification;
	}








	/**
	 * @param header
	 * @param request
	 * @return
	 */
	public static RequestSpecification getRequest(Map<String, String> header, HashMap<String, String> request) {
		try {
			// LOGGER.info("Path " + path);
			LOGGER.info("header " + header);
			requestSpecification = given().contentType(ContentType.JSON).baseUri(BASE_URL).headers(header)
					.body(request);

			printRequestLogInReport(requestSpecification);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestSpecification;
	}

	/**
	 * @param header
	 * @param request
	 * @return
	 */
	public static RequestSpecification getRequest(Map<String, String> header, JSONArray request) {
		try {
			// LOGGER.info("Path " + path);
			LOGGER.info("header " + header);
			requestSpecification = given().contentType(ContentType.JSON).baseUri(BASE_URL).headers(header)
					.body(request);
			printRequestLogInReport(requestSpecification);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestSpecification;
	}

	/**
	 * @author
	 * 
	 * @return RequestSpecification
	 */
	public static RequestSpecification getRequestWithPathParam(Map<String, String> pathParam) {
		try {
			// LOGGER.info("Path " + path);
			LOGGER.info("Path param" + pathParam);
			requestSpecification = given().contentType(ContentType.URLENC.withCharset("UTF-8")).baseUri(BASE_URL)
					.pathParams(pathParam);
			printRequestLogInReport(requestSpecification);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("requestSpecification : " + requestSpecification);
		return requestSpecification;
	}

	/**
	 * @author
	 * @param header
	 * @param path
	 * @return RequestSpecification
	 */
	public RequestSpecification getRequest(Map<String, String> header, String path) {
		try {
			LOGGER.info("Path " + path);
			LOGGER.info("header " + header);
			requestSpecification = given().contentType(ContentType.URLENC.withCharset("UTF-8"))
					.baseUri(BASE_URL + path);
			printRequestLogInReport(requestSpecification);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestSpecification;
	}


	/**
	 * @author
	 * @param path
	 * @param formParam
	 * @return RequestSpecification
	 */
	public static RequestSpecification getRequest(String path, Map<String, String> formParam) {
		try {
			LOGGER.info("Path " + path);
			LOGGER.info("formParam " + formParam);
			requestSpecification = given().contentType(ContentType.URLENC.withCharset("UTF-8")).baseUri(BASE_URL + path)
					.formParams(formParam);
			printRequestLogInReport(requestSpecification);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestSpecification;
	}

	/**
	 * @param queryParam
	 * @return
	 */
	public static RequestSpecification GetRequestWithQueryParam(Map<String,String>header,Map<String, Integer> queryParam) {
		try {
			//LOGGER.info("Path " + path);
			LOGGER.info("queryParam " + queryParam);
			requestSpecification = given().contentType(ContentType.JSON).baseUri(BASE_URL).headers(header)
					.queryParams(queryParam);
			printRequestLogInReport(requestSpecification);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestSpecification;
	}



	public static RequestSpecification getRequestWithQueryParam(Map<String, String> queryParam) {
		try {
			//LOGGER.info("Path " + path);
			LOGGER.info("queryParam " + queryParam);
			requestSpecification = given().contentType(ContentType.JSON).baseUri(BASE_URL)
					.queryParams(queryParam);
			printRequestLogInReport(requestSpecification);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestSpecification;
	}

	public static RequestSpecification getRequestWithQueryParam(Map<String,String>header,Map<String, String> queryParam) {
		try {
			//LOGGER.info("Path " + path);
			LOGGER.info("queryParam " + queryParam);
			requestSpecification = given().contentType(ContentType.JSON).baseUri(BASE_URL).headers(header)
					.queryParams(queryParam);
			printRequestLogInReport(requestSpecification);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestSpecification;
	}

	/**
	 * @author
	 * @param path
	 * @param request
	 * @param queryParam
	 * @return RequestSpecification
	 */
	public static RequestSpecification getRequest(String path, Object request, Map<String, String> queryParam) {
		try {
			LOGGER.info("Path " + path);
			LOGGER.info("request " + request.toString());
			LOGGER.info("queryParam " + queryParam);
			requestSpecification = given().contentType(ContentType.JSON).baseUri(BASE_URL + path)
					.queryParams(queryParam).body(request);
			printRequestLogInReport(requestSpecification);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestSpecification;
	}

	/**
	 * @author
	 * @param path
	 * @param pathParam
	 * @param request
	 * @return
	 */
	public static RequestSpecification getRequest(String path, Map<String, String> pathParam, Object request) {
		try {
			LOGGER.info("Path " + path);
			LOGGER.info("request " + request.toString());
			LOGGER.info("pathParam " + pathParam);
			requestSpecification = given().contentType(ContentType.JSON).baseUri(BASE_URL + path).pathParams(pathParam)
					.body(request);
			printRequestLogInReport(requestSpecification);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestSpecification;
	}

	/**
	 * @author
	 * @param header
	 * @param pathParam
	 * @return RequestSpecification
	 */
	public static RequestSpecification getRequest(Map<String, String> header, Map<String, String> pathParam) {
		try {
//			LOGGER.info("Path " + path);
			LOGGER.info("header " + header);
			LOGGER.info("pathParam " + pathParam);
			requestSpecification = given().contentType(ContentType.JSON).baseUri(BASE_URL).headers(header)
					.pathParams(pathParam);
			printRequestLogInReport(requestSpecification);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestSpecification;
	}

	/**
	 * @author
	 * @param header
	 * @param path
	 * @param queryParam
	 * @param pathParam
	 * @return RequestSpecification
	 */
	public static RequestSpecification getRequest(Map<String, String> header, String path,
			Map<String, String> queryParam, Map<String, String> pathParam) {
		try {
			LOGGER.info("Path " + path);
			LOGGER.info("header " + header);
			LOGGER.info("queryParam " + queryParam);
			LOGGER.info("pathParam " + pathParam);
			requestSpecification = given().contentType(ContentType.JSON).baseUri(BASE_URL + path).headers(header)
					.queryParams(queryParam).pathParams(pathParam);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestSpecification;
	}

	/*	*//**
			 * @author
			 * 
			 * @param path
			 * @param queryParam
			 * @param pathParam
			 * @return RequestSpecification
			 *//*
				 * public static RequestSpecification getRequest(Map<String, String> queryParam,
				 * Map<String, String> pathParam,) { try { LOGGER.info("Path " + path);
				 * LOGGER.info("queryParam " + queryParam); LOGGER.info("pathParam " +
				 * pathParam); requestSpecification =
				 * given().contentType(ContentType.JSON).baseUri(BASE_URL + path)
				 * .queryParams(queryParam).pathParams(pathParam); } catch (Exception e) {
				 * e.printStackTrace(); } return requestSpecification; }
				 */



    /**
     *
     * @param header
     * @param formParam
     * @param key
     * @param file
     * @return
     */
    public static RequestSpecification getRequestHeaderFormParamWithMultiPart(Map<String, String> header, Map<String, String> formParam, String key, File file) {
        try {
            // LOGGER.info("Path " + path);
             LOGGER.info("header " + header);
            LOGGER.info("queryParam " + formParam);
            requestSpecification = given().baseUri(BASE_URL).headers(header)
                    .formParams(formParam).multiPart(key,file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return requestSpecification;
    }

    public static RequestSpecification getRequestHeaderFormParamWithMultiPart(Map<String, String> header, Map<String, String> formParam,
                                                                              String key1, File file1,String key2, File file2) {
        try {
            // LOGGER.info("Path " + path);
            LOGGER.info("header " + header);
            LOGGER.info("queryParam " + formParam);
            requestSpecification = given().baseUri(BASE_URL).headers(header)
                    .formParams(formParam).multiPart(key1,file1).multiPart(key2,file2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return requestSpecification;
    }

    public static RequestSpecification getRequestHeaderFormParamWithMultiPart( Map<String, String> formParam,
                                                                              String key1, File file1,String key2, File file2) {
        try {
            // LOGGER.info("Path " + path);
//            LOGGER.info("header " + header);
            LOGGER.info("queryParam " + formParam);
            requestSpecification = given().baseUri(BASE_URL)
                    .formParams(formParam).multiPart(key1,file1).multiPart(key2,file2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return requestSpecification;
    }

	public static RequestSpecification getRequestHeaderFormParamWithMultiPart( Map<String, String> header, String key1, File file1) {
		try {
			// LOGGER.info("Path " + path);
//            LOGGER.info("header " + header);
			LOGGER.info("queryParam " + header);
			requestSpecification = given().baseUri(BASE_URL)
					.headers(header).multiPart(key1,file1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestSpecification;
	}

    /**
     *
     * @param header
     * @param formParam
     * @return
     */
    public static RequestSpecification getRequestHeaderFormParam(Map<String, String> header, Map<String, String> formParam) {
        try {
            // LOGGER.info("Path " + path);
            LOGGER.info("header " + header);
            LOGGER.info("queryParam " + formParam);
            requestSpecification = given().baseUri(BASE_URL).headers(header)
                    .formParams(formParam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return requestSpecification;
    }


	public static RequestSpecification getRequestHeaderFormParam(Map<String, String> header) {
		try {
			// LOGGER.info("Path " + path);
			LOGGER.info("header " + header);
//			LOGGER.info("queryParam " + formParam);
			requestSpecification = given().baseUri(BASE_URL).headers(header);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestSpecification;
	}



	/**
     *
     * @param header
     * @return
     */
    public static RequestSpecification getRequestHeaderFormParam(Map<String, String> header, String key, File file) {
        try {

            LOGGER.info("header " + header);
            requestSpecification = given().baseUri(BASE_URL).headers(header).multiPart(key,file);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return requestSpecification;
    }

	public static RequestSpecification getRequestHeaderFormParam( String key, File file) {
		try {

//			LOGGER.info("header " + header);
			requestSpecification = given().baseUri(BASE_URL).multiPart(key,file);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestSpecification;
	}

    public static RequestSpecification getRequestHeaderFormParam( String key, File file,Map<String, String> formParam) {
        try {
            // LOGGER.info("Path " + path);
//            LOGGER.info("header " + header);
            LOGGER.info("queryParam " + formParam);
            requestSpecification = given().baseUri(BASE_URL)
                    .formParams(formParam).multiPart(key,file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return requestSpecification;
    }


    /**
     * @author
     * @param header
     * @param path
     * @param queryParam
     * @param request
     * @return RequestSpecification
     *//*
		 * public static RequestSpecification getRequest( Map<String, String> header,
		 * Map<String, String> queryParam, Object request) { try { //LOGGER.info("Path "
		 * + path); LOGGER.info("header " + header); LOGGER.info("queryParam " +
		 * queryParam); requestSpecification =
		 * given().contentType(ContentType.JSON).baseUri(BASE_URL).headers(header)
		 * .queryParams(queryParam).body(request); } catch (Exception e) {
		 * e.printStackTrace(); } return requestSpecification; }
		 */

	/**
	 * @author
	 * @param header
	 * @param pathParam
	 * @param request
	 * @return RequestSpecification
	 */
	public static RequestSpecification getRequest(Map<String, String> header, Map<String, String> pathParam,
			Object request) {
		try {
			// LOGGER.info("Path " + path);
			LOGGER.info("header " + header);
			LOGGER.info("pathParam " + pathParam);
			requestSpecification = given().contentType(ContentType.JSON).baseUri(BASE_URL).headers(header)
					.pathParams(pathParam).body(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestSpecification;
	}

	/**
	 * @author
	 * @param header
	 * @param pathParam
	 * @param request
	 * @param queryParam
	 * @return RequestSpecification
	 */
	public static RequestSpecification getRequest(Map<String, String> header, Map<String, String> pathParam,
			Map<String, String> queryParam, Object request) {
		try {
			// LOGGER.info("Path " + path);
			LOGGER.info("header " + header);
			LOGGER.info("pathParam " + pathParam);
			requestSpecification = given().contentType(ContentType.JSON).baseUri(BASE_URL).headers(header)
					.queryParams(queryParam).pathParams(pathParam).body(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestSpecification;
	}

//setting report values
		private static void printRequestLogInReport(RequestSpecification requestSpecification) {
			QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(requestSpecification);
			ExtentReportManager.logInfoDetails("Base Url is :" + queryableRequestSpecification.getURI());
			ExtentReportManager.logInfoDetails("Headers are :" + queryableRequestSpecification.getHeaders().asList());
			//	ExtentReportManager.logInfoDetails("Request body is :" + queryableRequestSpecification.getBody());
			if (queryableRequestSpecification.getBody() != null) {
				ExtentReportManager.logInfoDetails("Request Body is - ");
				ExtentReportManager.logJson(queryableRequestSpecification.getBody());
			}
		}

	public static void printResponseLogInReport(Response response) {
		ExtentReportManager.logInfoDetails("Response status is :" + response.getStatusCode());
		// ExtentReportManager.logInfoDetails("Response Headers are ");
		// ExtentReportManager.logHeaders(response.getHeaders().asList().toString());

		ExtentReportManager.logJson(response.getBody().prettyPrint());
	}

    public static RequestSpecification getRequestBy(Map<String, String> header, JSONObject request) {
        try {
            // LOGGER.info("Path " + path);
            LOGGER.info("header " + header);
            requestSpecification = given().contentType(ContentType.JSON).baseUri(BASE_URL).headers(header)
                    .body(request);

            printRequestLogInReport(requestSpecification);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return requestSpecification;
    }

    public static RequestSpecification getRequestOf(Map<String, String> header, Map<String, Integer> queryParam) {
        try {
            // LOGGER.info("Path " + path);
            // LOGGER.info("header " + header);
            LOGGER.info("queryParam " + queryParam);
            requestSpecification = given().contentType(ContentType.JSON).baseUri(BASE_URL).headers(header)
                    .queryParams(queryParam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return requestSpecification;
    }

	public static RequestSpecification getRequestOfTwo(Map<String, String> header, Map<String, String> queryParam) {
		try {
			// LOGGER.info("Path " + path);
			// LOGGER.info("header " + header);
			LOGGER.info("queryParam " + queryParam);
			requestSpecification = given().contentType(ContentType.JSON).baseUri(BASE_URL).headers(header)
					.queryParams(queryParam);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestSpecification;
	}

	public static RequestSpecification getRequestOfTwo( Map<String, String> queryParam) {
		try {
			// LOGGER.info("Path " + path);
			// LOGGER.info("header " + header);
			LOGGER.info("queryParam " + queryParam);
			requestSpecification = given().contentType(ContentType.JSON).baseUri(BASE_URL)
					.queryParams(queryParam);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestSpecification;
	}


	public static RequestSpecification getRequestOfLong(Map<String, String> header, Map<String, Long> queryParam) {
        try {
            // LOGGER.info("Path " + path);
            // LOGGER.info("header " + header);
            LOGGER.info("queryParam " + queryParam);
            requestSpecification = given().contentType(ContentType.JSON).baseUri(BASE_URL).headers(header)
                    .queryParams(queryParam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return requestSpecification;
    }

    public static RequestSpecification getRequestOfLong(Map<String, Long> queryParam) {
        try {
            // LOGGER.info("Path " + path);
            // LOGGER.info("header " + header);
            LOGGER.info("queryParam " + queryParam);
            requestSpecification = given().contentType(ContentType.JSON).baseUri(BASE_URL)
                    .queryParams(queryParam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return requestSpecification;
    }


    public static RequestSpecification getRequestOf(Map<String, Integer> queryParam) {
        try {
            // LOGGER.info("Path " + path);
            // LOGGER.info("header " + header);
            LOGGER.info("queryParam " + queryParam);
            requestSpecification = given().contentType(ContentType.JSON).baseUri(BASE_URL).queryParams(queryParam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return requestSpecification;
    }

    public static RequestSpecification getRequestOfWithString(Map<String, String> header, Map<String, String> queryParam) {
        try {
            // LOGGER.info("Path " + path);
            // LOGGER.info("header " + header);
            LOGGER.info("queryParam " + queryParam);
            requestSpecification = given().contentType(ContentType.JSON).baseUri(BASE_URL).headers(header)
                    .queryParams(queryParam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return requestSpecification;
    }

    public static RequestSpecification getRequestOfWithString( Map<String, String> queryParam) {
        try {
            // LOGGER.info("Path " + path);
            // LOGGER.info("header " + header);
            LOGGER.info("queryParam " + queryParam);
            requestSpecification = given().contentType(ContentType.JSON).baseUri(BASE_URL)
                    .queryParams(queryParam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return requestSpecification;
    }




}