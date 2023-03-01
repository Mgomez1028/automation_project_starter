Feature: Data Fabric migration from the Dominican Republic DOMAIN: DO_CONSUMER_IDENTITY

  In order to migrate historical data from Dominican Republic
  As a data fabric
  I want to be able to process and validate the results

  Scenario: Should be able to load data for the JCE_REGISTRATION Subdomain
    Given the data is uploaded to an GCP bucket named dprep-lz-cat-int-lat-3f and contains 1 records
    And the ingestion process run in BATCH mode

  Scenario: Should be able to validate data transformation for JCE_REGISTRATION Subdomain
    Given the JOURNALING services run successfully
    Given that the data has been correctly stored



