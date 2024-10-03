package ru.everlast1ngw1nter.NauJava.configs;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.everlast1ngw1nter.NauJava.models.Product;

@Configuration
public class DbConfig
{
    @Bean
    @Scope(value = BeanDefinition.SCOPE_SINGLETON)
    public Map<Long, Product> productContainer()
    {
        return new HashMap<>();
    }
}
