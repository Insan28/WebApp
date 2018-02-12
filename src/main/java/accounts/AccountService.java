package accounts;
import java.util.Map;
import java.util.HashMap;

public class AccountService {
    private final Map<String, UserProfile> loginToProfile;
    private final Map<String, UserProfile> sessionIdToProfile;
    public AccountService()
    {
        loginToProfile = new HashMap<String, UserProfile>();
        sessionIdToProfile = new HashMap<String, UserProfile>();
    }

    public void AddNewUser(UserProfile userProfile)
    {
        loginToProfile.put(userProfile.getLogin(), userProfile);
    }

    public UserProfile getUserByLogin(String login)
    {
        return loginToProfile.get(login);
    }

    public UserProfile getUserBySessionId(String sessionId)
    {
        return sessionIdToProfile.get(sessionId);
    }

    public void AddSession(String sessionId, UserProfile userProfile)
    {
        sessionIdToProfile.put(sessionId, userProfile);
    }
    public void DeleteSession(String sesionId)
    {
        sessionIdToProfile.remove(sesionId);
    }

    public void DeleteUser(String login)
    {
        loginToProfile.remove(login);
    }
}
