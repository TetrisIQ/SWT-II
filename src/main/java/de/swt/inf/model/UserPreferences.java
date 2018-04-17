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
        //TO DO
    }

    public void setUniversity(UniversityEnum university) {
        //TO DO
    }

    public void setCourse(CourseEnum course) {
        //TO DO
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public void setMusic(MusicPreferencesEnum music) {
        //TO DO
    }

    public void setGender(GenderEnum gender) {
        //TO DO
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ProvinceEnum getProvince() { return this.provinceEnum;}

    public UniversityEnum getUniversityEnum() { return this.universityEnum;}

    public CourseEnum getCourseEnum() {
        return this.courseEnum;
    }
}

