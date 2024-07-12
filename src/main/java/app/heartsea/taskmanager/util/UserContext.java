package app.heartsea.taskmanager.util;

public class UserContext {
    private static final ThreadLocal<Object> userThreadLocal = new ThreadLocal<>();

    public static void setUser(Object user) {
        userThreadLocal.set(user);
    }

    public static Object getUser() {
        return userThreadLocal.get();
    }

    public static void clear() {
        userThreadLocal.remove();
    }
}
