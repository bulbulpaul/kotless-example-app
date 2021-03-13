## Kotless example app
Kotless demo app for [nakanoshima.dev #14](https://nakanoshima-dev.connpass.com/event/204733/)

Presentation slide -> [Speaker Deck](https://speakerdeck.com/bulbulpaul/kotlessdehazimeruserverlessapurikesiyonkai-fa)

### Environment variable

build.gradle.kts use bellow AWS settings.

| name | description | example |
| --- | --- | --- |
|KOTLESS_AWS_BUCKET | Jar and Terraform state file upload destination S3 bucket | example-XXXX |
|KOTLESS_AWS_PREFIX | resource name  prefix for aws | translate |
|KOTLESS_TERRAFORM_PROFILE | name of the profile to access AWS | default |
|KOTLESS_TERRAFORM_REGION | AWS region name | ap-northeast-1 |
