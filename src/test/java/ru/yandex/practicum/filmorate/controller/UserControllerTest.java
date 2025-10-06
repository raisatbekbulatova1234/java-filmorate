//package ru.yandex.practicum.filmorate.controller;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class UserControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @BeforeEach
//    void setUp() {
//        // Здесь можно инициализировать состояние системы перед каждым тестом
//    }
//
//    @Test
//    void testCreateUser() throws Exception {
//        // Подготовка данных для создания пользователя
//        String userJson = "{\"login\": \"testUser\", \"name\": \"Test User\"}";
//
//        // Отправка POST-запроса на создание пользователя
//        mockMvc.perform(post("/users")
//                        .contentType("application/json")
//                        .content(userJson))
//                .andExpect(status().isCreated());
//    }
//
//    @Test
//    void testUpdateUser() throws Exception {
//        // Сначала создаем пользователя
//        String userJson = "{\"login\": \"testUser\", \"name\": \"Test User\"}";
//        mockMvc.perform(post("/users")
//                        .contentType("application/json")
//                        .content(userJson))
//                .andExpect(status().isCreated());
//
//        // Затем обновляем пользователя
//        userJson = "{\"id\": 1, \"login\": \"updatedUser\", \"name\": \"Updated User\"}";
//        mockMvc.perform(put("/users")
//                        .contentType("application/json")
//                        .content(userJson))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void testGetAllUsers() throws Exception {
//        // Создаем несколько пользователей
//        for (int i = 0; i < 3; i++) {
//            String userJson = String.format("{\"login\": \"user%d\", \"name\": \"User %d\"}", i, i);
//            mockMvc.perform(post("/users")
//                            .contentType("application/json")
//                            .content(userJson))
//                    .andExpect(status().isCreated());
//        }
//
//        // Получаем список всех пользователей
//        mockMvc.perform(get("/users"))
//                .andExpect(status().isOk());
//    }
//}
