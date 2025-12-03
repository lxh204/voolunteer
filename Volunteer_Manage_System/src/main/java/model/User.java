package model;

/**
 * 用户数据模型（User JavaBean）。
 * 用于封装用户的基本信息、登入凭证和角色权限。
 */
public class User {
    private int userId;           // 用户ID (主键)
    private String username;      // 登入名/注册名
    private String password;      // 密码 (通常存储其哈希值)
    private String email;         // 邮箱
    private String role;          // 用户角色 (例如: "volunteer", "admin")

    // ------------------------------------------------------------------
    // 1. 构造函数
    // ------------------------------------------------------------------

    // 无参构造函数 (必须，方便框架反射实例化)
    public User() {
    }

    // 常用构造函数 (用于注册等场景，不带ID)
    public User(String username, String password, String email, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    // ------------------------------------------------------------------
    // 2. Getters 和 Setters (访问器和修改器)
    // ------------------------------------------------------------------

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    /**
     * 注意：在实际应用中，设置密码时应先对其进行哈希处理（如使用 BCrypt）。
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // ------------------------------------------------------------------
    // 3. 辅助方法 (可选)
    // ------------------------------------------------------------------

    // @Override
    // public String toString() {
    //     // 方便调试时打印对象信息，但不应包含密码！
    //     return "User [userId=" + userId + ", username=" + username + ", role=" + role + "]";
    // }
}