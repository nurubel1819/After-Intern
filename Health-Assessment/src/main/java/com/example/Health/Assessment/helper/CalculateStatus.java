package com.example.Health.Assessment.helper;

import com.example.Health.Assessment.dto.StatusDto;

public class CalculateStatus {
    public static StatusDto diseaseStatus(String diseaseName, int totalScore){
        if(diseaseName.equals("Anemia"))
        {
            StatusDto statusDto = new StatusDto();
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
        return null;
    }
}
