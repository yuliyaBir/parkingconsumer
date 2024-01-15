package be.vdab.parkingconsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ParkingListener {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private record ParkingMessage(LocalDateTime moment, int vrijePlaatsen){}
    @KafkaListener(topics = "parkings")
    void verwerkVrijePlaatsen(@Header(KafkaHeaders.RECEIVED_KEY) String key, @Payload ParkingMessage data){
        logger.info("Parking " + key + " heeft " + data.vrijePlaatsen + " vrije plaatsen op " + data.moment);
    }

}
