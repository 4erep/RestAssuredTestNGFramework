//package com.spotify.oauth2.tests;
//
//import com.spotify.oauth2.api.applicationApi.PlaylistApi;
//import com.spotify.oauth2.pojo.Error;
//import com.spotify.oauth2.pojo.Playlist;
//import com.spotify.oauth2.utils.DataLoader;
//import io.restassured.response.Response;
//import org.testng.annotations.Test;
//
//import static org.hamcrest.CoreMatchers.equalTo;
//import static org.hamcrest.MatcherAssert.assertThat;
//
//public class PlaylistwithOldMethods {
//
//        public Playlist playlistBuilder(String name, String description, boolean ispublic) {
//            return Playlist.builder().
//                    name(name).
//                    description(description).
//                    _public(ispublic).build();
//
//        }
//
//        public void assertPlaylistEqual(Playlist responsePlaylist, Playlist request_playlist) {
//            assertThat(responsePlaylist.getName(), equalTo(request_playlist.getName()));
//            assertThat(responsePlaylist.getDescription(), equalTo(request_playlist.getDescription()));
//            assertThat(responsePlaylist.get_public(), equalTo(request_playlist.get_public()));
//        }
//
//        public void assertStatusCode(int actualStatusCode, int expectedStatusCode) {
//            assertThat(actualStatusCode, equalTo(expectedStatusCode));
//        }
//
//        public void assertError(Error responseError, int expectedStatusCode, String expectedMessage) {
//            assertThat(responseError.getError().getStatus(), equalTo(expectedStatusCode));
//            assertThat(responseError.getError().getMessage(), equalTo(expectedMessage));
//
//        }
//
//
//        @Test
//        public void ShouldBeAbleToCreateAPlayList() {
//            Playlist request_playlist = playlistBuilder("New Playlist", "New playlist description", false);
//
//            Response response = PlaylistApi.post(request_playlist);
//
//            assertStatusCode(response.statusCode(), 201);
//
//            assertPlaylistEqual(response.as(Playlist.class), request_playlist);
//
//
//        }
//
//        @Test
//        public void ShouldBeAbleToGetAPlayList() {
//            Playlist requestPlaylist = new Playlist();
//            requestPlaylist.setName("New Playlist3");
//            requestPlaylist.setDescription("New playlist description3");
//            requestPlaylist.setPublic(false);
//
//            Response response = PlaylistApi.get(DataLoader.getInstance().getPlaylistId());
//            assertThat(response.statusCode(), equalTo(200));
//
//            Playlist responsePlaylist = response.as(Playlist.class);
//
//            assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
//            assertThat(responsePlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
//            assertThat(responsePlaylist.getPublic(), equalTo(requestPlaylist.getPublic()));
//
//        }
//
//        @Test
//        public void ShouldBeAbleToUpdateAPlayList() {
//            Playlist requestPlaylist = new Playlist();
//            requestPlaylist.setName("New Playlist6");
//            requestPlaylist.setDescription("New playlist description6");
//            requestPlaylist.setPublic(false);
//
//            Response response = PlaylistApi.update(DataLoader.getInstance().getUpdatePlaylistId(), requestPlaylist);
//            assertThat(response.statusCode(), equalTo(200));
//
//
//        }
//
//        @Test
//        public void ShouldNotBeAbleToCreateAPlayListWithoutName() {
//            Playlist requestPlaylist = new Playlist();
//            requestPlaylist.setName("");
//            requestPlaylist.setDescription("New playlist description");
//            requestPlaylist.setPublic(false);
//
//            Response response = PlaylistApi.post(requestPlaylist);
//            assertThat(response.statusCode(), equalTo(400));
//
//            assertError(response.as(Error.class), 400, "Missing required field: name");
//
//        }
//
//        @Test
//        public void ShouldNotBeAbleToCreateAPlayListWithExpiredToken() {
//            String invalid_token = "12345";
//            Playlist requestPlaylist = new Playlist();
//            requestPlaylist.setName("");
//            requestPlaylist.setDescription("New playlist description");
//            requestPlaylist.setPublic(false);
//
//            Response response = PlaylistApi.post(invalid_token, requestPlaylist);
//
//            assertThat(response.statusCode(), equalTo(401));
//            Error error = response.as(Error.class);
//
//            assertThat(error.getError().getStatus(), equalTo(401));
//            assertThat(error.getError().getMessage(), equalTo("Invalid access token"));
//
//
//        }
//    }
//
//}
