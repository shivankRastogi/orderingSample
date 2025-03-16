package org.shivank.models;

    import lombok.*;
    import org.shivank.enums.Gender;

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
