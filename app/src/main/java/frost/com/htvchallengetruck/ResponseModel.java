package frost.com.htvchallengetruck;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by chinmayghag on 11/02/18.
 */

public class ResponseModel {
        @SerializedName("id")
        @Expose
        private String response;



        public String getResponse() {
            return response;
        }

        public void setResponse(String response) {
            this.response = response;
        }
    }


