# variables.tf

variable "app_name" {
  description = "The name of the Heroku application"
  type        = string
  default     = "slhs"
}

variable "custom_env_value" {
  description = "Custom environment variable value"
  type        = string
  default     = "default_value"
}
