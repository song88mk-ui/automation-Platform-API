# automation-Platform-API

API automation for `Platform.API` (the `platform-final` repo), written in **Java and Groovy**.

**This is not a .NET project.** The dotnet-claude-kit skills, templates and conventions in the
root `../CLAUDE.md` do not apply here. Do not run the kit's scaffold or health-check
skills against this repo.

## Stack

Java 8 / Maven / [WebTau](https://testingisdocumenting.org/webtau/) + JUnit 4.
Artifact `Platform-API-Automation`.

Dependencies come from the Azure Artifacts feed `FCI-FEED`
(`https://pkgs.dev.azure.com/song88-fci/fci/_packaging/FCI-FEED/maven/v1`), not Maven Central
alone — a build will fail without access to it.

## Commands

```
mvn clean install           # build
mvn test                    # test, default targetEnvironment preprod
mvn test -Pprod             # test against prod
```

`targetEnvironment` defaults to `preprod` (`pom.xml:18`). The `preprod` and `prod` profiles
select the target host; `pom.xml:11-12` records `http://platform-api.song88.com`.

CI is Azure DevOps (`azure-pipelines.yml`), `pr: none`, triggered on `main` for changes to
`pom.xml` or `src`. It runs `Maven@3` goal `test` with `-P$(targetEnvironment)` and publishes
JUnit results from `**/surefire-reports/TEST-*.xml` under JDK 1.8.

## What is actually here

Only two files carry real content:

| File | Purpose |
|---|---|
| `src/main/groovy/webtau.cfg.groovy` | WebTau config — environments and base URLs |
| `src/main/groovy/scenarios/platform-topfixtures.groovy` | The one scenario, covering top fixtures |

`src/test/java/com/fsti/automation/AppTest.java` and `App.java` are **unmodified Maven
archetype stubs**. They are not real tests. Treat this repo as a proof of concept, not a
working suite.

## Relationship to the other test repos

Three separate suites target the same platform APIs, in three languages:

| Repo | Stack | Targets |
|---|---|---|
| this one | Java / WebTau | `preprod-platformapi.song88.com`, `prod-platformapi.song88.com` |
| `../api-bdd-tests` | Ruby / Cucumber | dev, both external DNS and in-cluster `*.platform.svc` |
| `../cuc-backend-tests` | Ruby / Cucumber | dev and production external DNS |

The Ruby suites are further developed and run in GitHub Actions. Before adding a scenario here,
check whether it belongs in `api-bdd-tests` instead.

Note that both Ruby suites currently `exit 0` regardless of result, so their green runs mean
nothing — see `../INTEGRATION-MAP.md`. This repo's Azure pipeline does publish real JUnit
results and can fail.
