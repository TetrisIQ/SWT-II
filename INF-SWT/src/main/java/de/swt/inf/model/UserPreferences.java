package de.swt.inf.model;

public class UserPreferences {

    private int USER_PREFERENCES_ID;

    private int semester;

    private int age;

    private User user;

    private CourseEnum courseEnum;

    private ProvinceEnum provinceEnum;

    private UniversityEnum universityEnum;

    private MusicPreferencesEnum musicPreferencesEnum;

    private GenderEnum genderEnum;

    public UserPreferences() {

    }

    public void setProvince(ProvinceEnum province) {
        this.provinceEnum = province;
    }

    public void setUniversity(UniversityEnum university) {
        this.universityEnum = university;
    }

    public void setCourse(CourseEnum course) {
        this.courseEnum = course;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public void setMusic(MusicPreferencesEnum music) {
        this.musicPreferencesEnum = music;
    }

    public void setGender(GenderEnum gender) {
        this.genderEnum = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
