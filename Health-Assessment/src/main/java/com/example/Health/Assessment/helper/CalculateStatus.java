package com.example.Health.Assessment.helper;

import com.example.Health.Assessment.dto.StatusDto;

public class CalculateStatus {
    public static StatusDto diseaseStatus(String diseaseName, int totalScore){
        StatusDto statusDto = new StatusDto();
        if(diseaseName.equals("Anemia"))
        {
            statusDto.setDiseaseName(diseaseName);
            if(totalScore==0)
            {
                statusDto.setStatus("No Risk");
                statusDto.setColorCode("#00C853");
                statusDto.setAdvice("No significant symptoms, maintain a balanced diet.");
            }
            else if(totalScore==1)
            {
                statusDto.setStatus("Low Risk");
                statusDto.setColorCode("#75DD75");
                statusDto.setAdvice("No significant symptoms, maintain a balanced diet.");
            }
            else if(totalScore==2)
            {
                statusDto.setStatus("Moderate Risk");
                statusDto.setColorCode("#FF3F3F");
                statusDto.setAdvice("Possible mild anemia, monitor symptoms, increase iron intake.");
            }
            else if(totalScore==3)
            {
                statusDto.setStatus("High Risk");
                statusDto.setColorCode("#F13546");
                statusDto.setAdvice("Severe symptoms, consult a doctor for blood tests.");
            }
            return statusDto;
        }
        else if(diseaseName.equals("Diabetes"))
        {
            statusDto.setDiseaseName(diseaseName);
            if(totalScore==0)
            {
                statusDto.setStatus("No Risk");
                statusDto.setColorCode("#00C853");
                statusDto.setAdvice("No immediate risk, continue a healthy lifestyle.");
            }
            else if(totalScore==1)
            {
                statusDto.setStatus("Low Risk");
                statusDto.setColorCode("#75DD75");
                statusDto.setAdvice("No immediate risk, continue a healthy lifestyle.");
            }
            else if(totalScore==2)
            {
                statusDto.setStatus("Moderate Risk");
                statusDto.setColorCode("#FF3F3F");
                statusDto.setAdvice("Early signs, monitor blood sugar levels.");
            }
            else if(totalScore==3)
            {
                statusDto.setStatus("Moderate Risk");
                statusDto.setColorCode("#FF3F3F");
                statusDto.setAdvice("Early signs, monitor blood sugar levels.");
            }
            else if(totalScore==4)
            {
                statusDto.setStatus("High Risk");
                statusDto.setColorCode("#F13546");
                statusDto.setAdvice("High risk of diabetes, seek medical evaluation.");
            }
            return statusDto;
        }
        return null;
    }
}
