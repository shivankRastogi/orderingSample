package org.example.models;

    import lombok.*;
    import org.example.enums.Gender;

    @Data
    @Builder
    @NoArgsConstructor
    @Getter
    @Setter
    @AllArgsConstructor
    public class UserDetails {
        private String name;
        private Gender gender;
        private String phoneNumber;
        private String pincode;

    }
