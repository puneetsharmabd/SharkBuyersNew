package com.sharkbuyers.ui.mainActivity;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllBuyersResponseModel implements Parcelable {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    public final static Parcelable.Creator<AllBuyersResponseModel> CREATOR = new Creator<AllBuyersResponseModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public AllBuyersResponseModel createFromParcel(Parcel in) {
            return new AllBuyersResponseModel(in);
        }

        public AllBuyersResponseModel[] newArray(int size) {
            return (new AllBuyersResponseModel[size]);
        }

    };

    protected AllBuyersResponseModel(Parcel in) {
        this.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.data, (Datum.class.getClassLoader()));
    }

    public AllBuyersResponseModel() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(message);
        dest.writeList(data);
    }

    public int describeContents() {
        return 0;
    }
    public static class Datum implements Parcelable
    {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("first_name")
        @Expose
        private String firstName;
        @SerializedName("last_name")
        @Expose
        private String lastName;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("phone_num")
        @Expose
        private String phoneNum;
        @SerializedName("dob")
        @Expose
        private String dob;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("occupation")
        @Expose
        private String occupation;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("brand_image")
        @Expose
        private String brandImage;
        @SerializedName("resume")
        @Expose
        private String resume;
        @SerializedName("email_verified_at")
        @Expose
        private String emailVerifiedAt;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("latitude")
        @Expose
        private String latitude;
        @SerializedName("longitude")
        @Expose
        private String longitude;
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("state")
        @Expose
        private State state;
        @SerializedName("country")
        @Expose
        private Country country;
        @SerializedName("verify_status")
        @Expose
        private Integer verifyStatus;
        @SerializedName("verify_code")
        @Expose
        private String verifyCode;
        @SerializedName("country_image")
        @Expose
        public String countryImage;
        public final static Parcelable.Creator<Datum> CREATOR = new Creator<Datum>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Datum createFromParcel(Parcel in) {
                return new Datum(in);
            }

            public Datum[] newArray(int size) {
                return (new Datum[size]);
            }

        }
                ;

        protected Datum(Parcel in) {
            this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.firstName = ((String) in.readValue((String.class.getClassLoader())));
            this.lastName = ((String) in.readValue((String.class.getClassLoader())));
            this.email = ((String) in.readValue((String.class.getClassLoader())));
            this.phoneNum = ((String) in.readValue((String.class.getClassLoader())));
            this.dob = ((String) in.readValue((String.class.getClassLoader())));
            this.gender = ((String) in.readValue((String.class.getClassLoader())));
            this.occupation = ((String) in.readValue((String.class.getClassLoader())));
            this.address = ((String) in.readValue((String.class.getClassLoader())));
            this.brandImage = ((String) in.readValue((String.class.getClassLoader())));
            this.resume = ((String) in.readValue((String.class.getClassLoader())));
            this.emailVerifiedAt = ((String) in.readValue((String.class.getClassLoader())));
            this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
            this.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
            this.latitude = ((String) in.readValue((String.class.getClassLoader())));
            this.longitude = ((String) in.readValue((String.class.getClassLoader())));
            this.city = ((String) in.readValue((String.class.getClassLoader())));
            this.state = ((State) in.readValue((State.class.getClassLoader())));
            this.country = ((Country) in.readValue((Country.class.getClassLoader())));
            this.verifyStatus = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.verifyCode = ((String) in.readValue((String.class.getClassLoader())));
            this.countryImage = ((String) in.readValue((String.class.getClassLoader())));
        }

        public Datum() {
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhoneNum() {
            return phoneNum;
        }

        public void setPhoneNum(String phoneNum) {
            this.phoneNum = phoneNum;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getOccupation() {
            return occupation;
        }

        public void setOccupation(String occupation) {
            this.occupation = occupation;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getBrandImage() {
            return brandImage;
        }

        public void setBrandImage(String brandImage) {
            this.brandImage = brandImage;
        }

        public String getResume() {
            return resume;
        }

        public void setResume(String resume) {
            this.resume = resume;
        }

        public String getEmailVerifiedAt() {
            return emailVerifiedAt;
        }

        public void setEmailVerifiedAt(String emailVerifiedAt) {
            this.emailVerifiedAt = emailVerifiedAt;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public State getState() {
            return state;
        }

        public void setState(State state) {
            this.state = state;
        }

        public Country getCountry() {
            return country;
        }

        public void setCountry(Country country) {
            this.country = country;
        }

        public Integer getVerifyStatus() {
            return verifyStatus;
        }

        public void setVerifyStatus(Integer verifyStatus) {
            this.verifyStatus = verifyStatus;
        }

        public String getVerifyCode() {
            return verifyCode;
        }

        public void setVerifyCode(String verifyCode) {
            this.verifyCode = verifyCode;
        }

        public String getCountryImage() {
            return countryImage;
        }

        public void setCountryImage(String countryImage) {
            this.countryImage = countryImage;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(firstName);
            dest.writeValue(lastName);
            dest.writeValue(email);
            dest.writeValue(phoneNum);
            dest.writeValue(dob);
            dest.writeValue(gender);
            dest.writeValue(occupation);
            dest.writeValue(address);
            dest.writeValue(brandImage);
            dest.writeValue(resume);
            dest.writeValue(emailVerifiedAt);
            dest.writeValue(createdAt);
            dest.writeValue(updatedAt);
            dest.writeValue(latitude);
            dest.writeValue(longitude);
            dest.writeValue(city);
            dest.writeValue(state);
            dest.writeValue(country);
            dest.writeValue(verifyStatus);
            dest.writeValue(verifyCode);
            dest.writeValue(countryImage);
        }

        public int describeContents() {
            return 0;
        }
        public static class State implements Parcelable
        {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("country_id")
            @Expose
            private Integer countryId;
            public final static Parcelable.Creator<State> CREATOR = new Creator<State>() {


                @SuppressWarnings({
                        "unchecked"
                })
                public State createFromParcel(Parcel in) {
                    return new State(in);
                }

                public State[] newArray(int size) {
                    return (new State[size]);
                }

            }
                    ;

            protected State(Parcel in) {
                this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
                this.name = ((String) in.readValue((String.class.getClassLoader())));
                this.countryId = ((Integer) in.readValue((Integer.class.getClassLoader())));
            }

            public State() {
            }

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Integer getCountryId() {
                return countryId;
            }

            public void setCountryId(Integer countryId) {
                this.countryId = countryId;
            }

            public void writeToParcel(Parcel dest, int flags) {
                dest.writeValue(id);
                dest.writeValue(name);
                dest.writeValue(countryId);
            }

            public int describeContents() {
                return 0;
            }

        }
        public static class Country implements Parcelable
        {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("sortname")
            @Expose
            private String sortname;
            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("phonecode")
            @Expose
            private Integer phonecode;
            public final static Parcelable.Creator<Country> CREATOR = new Creator<Country>() {


                @SuppressWarnings({
                        "unchecked"
                })
                public Country createFromParcel(Parcel in) {
                    return new Country(in);
                }

                public Country[] newArray(int size) {
                    return (new Country[size]);
                }

            }
                    ;

            protected Country(Parcel in) {
                this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
                this.sortname = ((String) in.readValue((String.class.getClassLoader())));
                this.name = ((String) in.readValue((String.class.getClassLoader())));
                this.phonecode = ((Integer) in.readValue((Integer.class.getClassLoader())));
            }

            public Country() {
            }

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getSortname() {
                return sortname;
            }

            public void setSortname(String sortname) {
                this.sortname = sortname;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Integer getPhonecode() {
                return phonecode;
            }

            public void setPhonecode(Integer phonecode) {
                this.phonecode = phonecode;
            }

            public void writeToParcel(Parcel dest, int flags) {
                dest.writeValue(id);
                dest.writeValue(sortname);
                dest.writeValue(name);
                dest.writeValue(phonecode);
            }

            public int describeContents() {
                return 0;
            }

        }
    }
}
