package com.example.be_swp.Data;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class RatingData {

    private Long appointmentId;

    private String serviceFeedback;
    private String expertFeedback;

    private Long serviceRating;
    private Long expertRating;
}
