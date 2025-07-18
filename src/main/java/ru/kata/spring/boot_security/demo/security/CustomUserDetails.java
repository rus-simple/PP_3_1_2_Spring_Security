package ru.kata.spring.boot_security.demo.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.kata.spring.boot_security.demo.entities.User;

import java.util.Collection;

/** * Класс CustomUserDetails реализует интерфейс UserDetails, представляющий информацию о пользователе, * используемую Spring Security для аутентификации и авторизации. */
public class CustomUserDetails implements UserDetails {

    private final User user;
    // Коллекция авторитетов (ролей/прав), реализующая GrantedAuthority
    private final Collection<? extends GrantedAuthority> authorities;

    /**
     * Конструктор класса, инициализирующий пользователя и его авторитеты
     * @param user - объект пользователя из базы данных
     * @param authorities - коллекция ролей/прав, реализующих GrantedAuthority
     */
    public CustomUserDetails(User user, Collection<? extends GrantedAuthority> authorities) {
        this.user = user;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities; //Возвращает коллекцию авторитетов (ролей/прав), связанных с этим пользователем
    }



    /** * Возвращает пароль пользователя, который был введен при регистрации. * * @return Пароль пользователя. */
    @Override
    public String getPassword() {
        return user.getPassword(); // Возвращаем пароль пользователя из сущности User.
    }

    /** * Возвращает уникальное имя пользователя (логин), используемое для идентификации пользователя. * * @return Имя пользователя. */
    @Override
    public String getUsername() {
        return user.getUsername(); // Возвращаем имя пользователя из сущности User.
    }

    /** * Проверяет, истек ли срок действия учетной записи пользователя. * В данном случае всегда возвращает true, предполагая, что учетная запись действительна. * * @return Всегда true. */
    @Override
    public boolean isAccountNonExpired() {
        return true; // Учетная запись не просрочена.
    }

    /** * Проверяет, заблокирована ли учетная запись пользователя. * В данном случае всегда возвращает true, предполагая, что учетная запись разблокирована. * * @return Всегда true. */
    @Override
    public boolean isAccountNonLocked() {
        return true; // Учетная запись не заблокирована.
    }

    /** * Проверяет, истёк ли срок действия пароля пользователя. * В данном случае всегда возвращает true, предполагая, что пароль действителен. * * @return Всегда true. */
    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Срок действия пароля не истек.
    }

    /** * Проверяет, включена ли учетная запись пользователя. * В данном случае всегда возвращает true, предполагая, что учетная запись активна. * * @return Всегда true. */
    @Override
    public boolean isEnabled() {
        return true; // Учетная запись включена.
    }
}
