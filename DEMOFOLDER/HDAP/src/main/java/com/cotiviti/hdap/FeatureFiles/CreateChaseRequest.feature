
@ChaseRequest
Feature: CreateChaseRequest API Validation
 	Scenario Outline: Validate Create Chase Request, schema validation
    When I create chase request with IntendedUse as <IntendedUse>, AuditType as <AuditType>, ChartType as <ChartType>, DOS as <DOS>, DOE as <DOE>, Prospectiveflag as <ProspectiveFlag>
    When I validate schema of Chase Request Response using file as <SchemaFile>
    Then I verify status code for chase request as 202
    Then I verify response message for chase request as Pushed to Queue
    Then I capture the chase requestId created
    Examples:
      | IntendedUse | AuditType | ChartType | DOS | DOE | ProspectiveFlag |SchemaFile|
      |RCA          |POS        |Medical    |2019-02-01|2025-01-01|false  |create-chasereq-schema.json|

   #Valid Intended use -(MRA,RCA,CCV,CV,CRA,RADV,HEDIS)
    Scenario: Validate with InCorrect Intended use
      When I create chase request with IntendedUse as DES, AuditType as POS, ChartType as Billing, DOS as 2020-09-20, DOE as 2025-02-20, Prospectiveflag as false
      Then I verify status code for chase request as 400
      Then I verify response message for chase request as BadRequest

   #Valid Audit Type(DRG,POS,RADM,SNF,IRF,IBR,SS,ER,ASC,HH,OP,APC,OBV)
  Scenario: Validate with InCorrect AuditType
    When I create chase request with IntendedUse as CRA, AuditType as hell, ChartType as Billing, DOS as 2020-09-20, DOE as 2025-02-20, Prospectiveflag as false
    Then I verify status code for chase request as 400
    Then I verify response message for chase request as BadRequest

    #valid ChartType(Medical,Billing,Pharmacy)
  Scenario: Validate with InCorrect ChartType
    When I create chase request with IntendedUse as CRA, AuditType as RADM, ChartType as BillTes, DOS as 2020-09-20, DOE as 2025-02-20, Prospectiveflag as false
    Then I verify status code for chase request as 400
    Then I verify response message for chase request as BadRequest

    #DOS start date must be less than current date
  Scenario: Validate with InCorrect DOSstartDate
    When I create chase request with IntendedUse as RADV, AuditType as ASC, ChartType as Billing, DOS as 2026-05-07, DOE as 2025-02-20, Prospectiveflag as false
    Then I verify status code for chase request as 400
    Then I verify response message for chase request as BadRequest

    #DOE end date must be less than current date
  Scenario: Validate with InCorrect DOEstartDate
    When I create chase request with c, AuditType as ASC, ChartType as Billing, DOS as 2019-05-07, DOE as 2026-06-02, Prospectiveflag as false
    Then I verify status code for chase request as 400
    Then I verify response message for chase request as BadRequest

		#Valid Intended use -(MRA,RCA,CCV,CV,CRA,RADV,HEDIS)
  Scenario: Validate with null Intended use
    When I create chase request with IntendedUse as null, AuditType as ASC, ChartType as Billing, DOS as 2019-05-07, DOE as 2026-06-02, Prospectiveflag as false
    Then I verify status code for chase request as 400
    Then I verify response message for chase request as BadRequest

 		#Valid Audit Type(DRG,POS,RADM,SNF,IRF,IBR,SS,ER,ASC,HH,OP,APC,OBV)
  Scenario: Validate with InCorrect AuditType
    When I create chase request with IntendedUse as CV, AuditType as null, ChartType as Billing, DOS as 2020-09-20, DOE as 2025-02-20, Prospectiveflag as false
    Then I verify status code for chase request as 400
    Then I verify response message for chase request as BadRequest
    
   #Valid Intended use -(MRA,RCA,CCV,CV,CRA,RADV,HEDIS)
  Scenario: Validate with null Cotiviti Claim Number
    When I create chase request with Cotiviti Claim Number as null, IntendedUse as RADV, AuditType as ASC, ChartType as Billing, DOS as 2019-05-07, DOE as 2025-05-01, Prospectiveflag as false
    Then I verify status code for chase request as 400
    Then I verify response message for chase request as BadRequest
   