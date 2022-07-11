url = "http://localhost:8080"

reportPath = "../../../target/test-classes/webtau-report.html"
userAgent = "Platform API Automation"


environments {
  preprod {
    url = "https://preprod-platformapi.song88.com"
  }

  production {
    url = "https://prod-platformapi.song88.com"
  }

}
