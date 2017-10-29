/*
 *This class used as the temporary data storage
 */

package com.example.testing;
import java.util.ArrayList;

public class temp_data_store {

    public static ArrayList<String> state_user_choose ;
    public static ArrayList<String>  language_user_choose_in_university_in_state;
    public static ArrayList<ArrayList<String>> university_state_choose;
    public static ArrayList<ArrayList<String>> all_data_state_and_university_after_language_choose;
    public static ArrayList<String> all_university_user_choose;
    public static ArrayList<ArrayList<String>> all_data_after_state_university_language;
    public static ArrayList<String> whether_on_campus_choose;
    public static ArrayList<ArrayList<String>> all_data_before_intensive;
    public static ArrayList<ArrayList<String>> all_data_of_states;
    public static ArrayList<ArrayList<String>> all_data_of_selected_universities;
    public static ArrayList<ArrayList<String>> final_search_result;
    public static ArrayList<ArrayList<String>> all_data_after_language_classify;
    public static ArrayList<ArrayList<String>> language_detail;
    public static ArrayList<String> language_user_choose;

    public static ArrayList<ArrayList<String>> getLanguage_detail() {
        return language_detail;
    }

    public static void setLanguage_detail(ArrayList<ArrayList<String>> language_detail) {
        temp_data_store.language_detail = language_detail;
    }


    public static ArrayList<ArrayList<String>> getAll_data_after_language_classify() {

        return all_data_after_language_classify;

    }

    public static void setAll_data_after_language_classify(ArrayList<ArrayList<String>> all_data_after_language_classify) {

        temp_data_store.all_data_after_language_classify = all_data_after_language_classify;

    }

    public static ArrayList<ArrayList<String>> getFinal_search_result() {

        return final_search_result;

    }

    public static void setFinal_search_result(ArrayList<ArrayList<String>> final_search_result) {

        temp_data_store.final_search_result = final_search_result;

    }

    public static ArrayList<ArrayList<String>> getAll_data_of_selected_universities() {

        return all_data_of_selected_universities;

    }

    public static void setAll_data_of_selected_universities(ArrayList<ArrayList<String>> all_data_of_selected_universities) {

        temp_data_store.all_data_of_selected_universities = all_data_of_selected_universities;

    }

    public static ArrayList<ArrayList<String>> getAll_data_of_states() {

        return all_data_of_states;

    }

    public static void setAll_data_of_states(ArrayList<ArrayList<String>> all_data_of_states) {

        temp_data_store.all_data_of_states = all_data_of_states;

    }

    public static ArrayList<ArrayList<String>> getAll_data_before_intensive() {

        return all_data_before_intensive;

    }

    public static void setAll_data_before_intensive(ArrayList<ArrayList<String>> all_data_before_intensive) {

        temp_data_store.all_data_before_intensive = all_data_before_intensive;

    }

    public static ArrayList<String> getWhether_on_campus_choose() {

        return whether_on_campus_choose;

    }

    public static void setWhether_on_campus_choose(ArrayList<String> whether_on_campus_choose) {

        temp_data_store.whether_on_campus_choose = whether_on_campus_choose;

    }

    public static ArrayList<ArrayList<String>> getAll_data_after_state_university_language() {

        return all_data_after_state_university_language;

    }

    public static void setAll_data_after_state_university_language(ArrayList<ArrayList<String>> all_data_after_state_university_language) {

        temp_data_store.all_data_after_state_university_language = all_data_after_state_university_language;

    }

    public static ArrayList<String> getAll_university_user_choose() {

        return all_university_user_choose;

    }

    public static void setAll_university_user_choose(ArrayList<String> all_university_user_choose) {

        temp_data_store.all_university_user_choose = all_university_user_choose;

    }


    public static void setAll_data_state_and_university_after_language_choose(ArrayList<ArrayList<String>> all_data_state_and_university_after_language_choose) {

        temp_data_store.all_data_state_and_university_after_language_choose = all_data_state_and_university_after_language_choose;

    }

    public static void setUniversity_state_choose(ArrayList<ArrayList<String>> university_state_choose) {

        temp_data_store.university_state_choose = university_state_choose;

    }



    public static ArrayList<String> getLanguage_user_choose() {
        return language_user_choose;
    }

    public static void setLanguage_user_choose(ArrayList<String> language_user_choose) {

        temp_data_store.language_user_choose = language_user_choose;

    }






    public static ArrayList<String> getState_user_choose() {
        return state_user_choose;
    }

    public static void setState_user_choose(ArrayList<String> state_user_choose) {

        temp_data_store.state_user_choose = state_user_choose;

    }

    public static void setLanguage_user_choose_in_university_in_state(ArrayList<String> language_user_choose_in_university_in_state) {

        temp_data_store.language_user_choose_in_university_in_state = language_user_choose_in_university_in_state;

    }

}
