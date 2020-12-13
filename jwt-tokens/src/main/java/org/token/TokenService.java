package org.token;

public interface TokenService<V> {
    public V decryptToken(String token);
    public String createToken(V data);
    public void setSecret(String secret);
}
