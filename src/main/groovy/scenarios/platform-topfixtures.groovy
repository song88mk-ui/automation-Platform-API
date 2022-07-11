package com.fsti.automation.scenarios

import static org.testingisdocumenting.webtau.WebTauGroovyDsl.*



scenario("Top Fixtures - No API Key provided.") {

  http.get("/v2/sportsbook/topfixtures") {
    header.statusCode.should == 400
    body.statusCode.should == 101
    body.message.should == "Missing Or Unsupported APIKey In Request Headers"
  }
}

scenario("Top Fixtures - With Headers provided.") {

  http.get("/v2/sportsbook/topfixtures",
      http.header(["accept": "application/json",
                   "x-api-key": "8LKvrh6C2fe8mKXZ",
                   "x-client-id": "71bbe7d8-fd04-459c-6d8b-08d9354e5b64",
                   "x-country-code": "HK",
                   "x-language-code": "EN"])) {
    header.statusCode.should == 200
    body.statusCode.should == 100
    body.message.should == "Success"
  }
}

