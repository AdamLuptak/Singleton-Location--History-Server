# main.tf

terraform {
  required_version = ">= 1.0"
  required_providers {
    heroku = {
      source  = "heroku/heroku"
      version = "~> 5.0"
    }
  }
}

provider "heroku" {
}

resource "heroku_app" "app" {
  name   = var.app_name
  region = "us" # Specify region
  stack  = "container" # Specify stack to be container for Docker-based app
}

resource "heroku_config" "app_config" {
  vars = {
    ENVIRONMENT   = "production"
    DATABASE_URL  = var.custom_env_value
  }
}

resource "heroku_app_config_association" "app_config_association" {
  app_id = heroku_app.app.id

  vars = heroku_config.app_config.vars
}

# # Optional: Add Heroku Postgres
# resource "heroku_addon" "postgres" {
#   app  = heroku_app.app.name
#   plan = "heroku-postgresql:hobby-dev"
# }

