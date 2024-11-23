package service.auth.util.help;

import java.time.Instant;

public class RandomCodeRedeem {

        private static long sequence = 0L;

        public static synchronized String generateId() {
            return Instant.now().toEpochMilli() + "_" + sequence++;
        }

}
