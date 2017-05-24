package hairsaon.utils;

/**
 * Created by Boris on 06.04.2017.
 * Интерфейс для добавления методов утилит.
 */
public interface IUtils {
    Boolean isLoginInfoExist(Object obj);

    String hashPassword(String rawPassword);

    String parsJwts(String token);

    String buildJwt(String email);


    Boolean isPasswordCorrect(String newPassword, String hashedPassrod);
}
