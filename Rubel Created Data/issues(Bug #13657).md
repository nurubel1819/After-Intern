# If dosage ID not given in request then duration isn't showing in response

# Solution

- got to class = PrescribedMedicationInterceptor
- got to method = public PrescribedMedicationDTO map(PrescribedMedication entity)
- Add 3 line before return dto
  if (entity.getDurationTime() != null)
  dto.setDurationTime(String.valueOf(entity.getDurationTime()));
  dto.setDurationUnit(entity.getDurationUnit());
- now it work properly.durationTime and durationUnit show in json

## solve process details

### First create new user(patient). Here show some error

- I try to save my information but this time show token and another user information.
- Then i use print() some different place.After long time i find a mistake in given json.
- In Json file present 'uuid' and it value is 'string'.
  if (exUser == null && dto.uuid != null) {
  val u = this.userService.findByUUID(dto.uuid!!)
  //this code for supporting app team to resolve same uuid data sync issues
  if (u.isPresent) return u.get() // throw ExceptionUtil.getAlreadyExist("UUID already exists!")
  }
- in this code 'uuid' is present in json data. As a result this if statement execute normally.
- But 'string' uuid value already present in database. As a result existing user come.
- So my information not upload in database.
- solution : set 'uuid' = null in json file.User(patient) upload successfully.

### Now find my user information form

- Api : /api/v6/users/{username}
- Here i set UserName to find user all information

### No go to main issue prescription-bundle upload

- Api : api/v3/prescription-bundle
- Set userId and patientId in given json but show error Doctor is not found
- Then take a doctor id='4147275' fro amir vai
- Then i login new user id and password that given amir vai
- After i again upload this json file.But error show item not found
- Then i go to Billing-Item Api and take a existing item and replace this item in given json.
- Now it show main issue "If dosage ID not given in request then duration isn't showing in response"

### Now try to solve main issue

- Api class name : GlobalPrescriptionBundleController and method is saveBundle.This is entry point of json
- Then i use multiple System.out.println() statement for finding where generate problem.
- I navigate dependent method after i realize that Entity save perfectly but dto not set show this.
- Navigation path = prescriptionBundleService.save(dto)->prescriptionService.createPrescription(prescriptionDto) -> prescriptionInterceptor.map(entity) -> meAsDst(entity, dto) -> medicationInterceptor.map(medicationList) -> dtos.add(map(dto)) ->
- Now my we come "PrescribedMedicationInterceptor" this class.
- Here present if (entity.getDosageId() != null) {
  MedicineDosages medicineDosages = medicineDosagesRepository.findById(entity.getDosageId()).orElse(null);
  if (medicineDosages != null) {
  dto.setDosageId(medicineDosages.getId());
  dto.setDosage(medicineDosages.getDosagesName());
  dto.setPrescribedQuantity(appBeanUtil.calculatePrescribedQuantity(medicineDosages.getUniteValue(), entity.getDurationTime(), entity.getDurationUnit()));

                dto.setNumberOfDays(appBeanUtil.calculateNumberOfPrescribedDays(entity.getDurationTime(), entity.getDurationUnit()));
                if (entity.getDurationTime() != null)
                    dto.setDurationTime(String.valueOf(entity.getDurationTime()));
                dto.setDurationUnit(entity.getDurationUnit());
            }
        }

- But my dosageId = null. As a result durationTime and durationUnit can't add dto.
  if (entity.getDurationTime() != null)
  dto.setDurationTime(String.valueOf(entity.getDurationTime()));
  dto.setDurationUnit(entity.getDurationUnit());
- add this three line before dto return.
