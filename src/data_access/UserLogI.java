package data_access;

import java.time.LocalDateTime;

public interface UserLogI {
    void writeLog(String user, LocalDateTime ldt, String result);
}
