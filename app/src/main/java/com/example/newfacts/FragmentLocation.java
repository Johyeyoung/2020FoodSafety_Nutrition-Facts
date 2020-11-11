package com.example.newfacts;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.Context.LOCATION_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

public class FragmentLocation extends Fragment {
    String[] permission_list = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    LocationManager locationManager;
    GoogleMap map;
    ArrayList<Double> lat_lst = new ArrayList<Double>();
    ArrayList<Double> lng_lst = new ArrayList<Double>();
    ArrayList<String> name_lst = new ArrayList<String>();
    ArrayList<String> vicinity_lst = new ArrayList<String>();
    ArrayList<Marker> marker_lst = new ArrayList<Marker>();

    ListView listView;
    MyAdapter adapter;
    Location currentLocation;
    Location cafeLocation;
    ActionBar actionBar;
    View header;
    androidx.appcompat.widget.AppCompatTextView title_bar;

    String url;
    boolean isRunning = false;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_location, container, false);
        //return inflater.inflate(R.layout.fragment_location, container, false);
        // custom title_bar
        //actionBar.setTitle("주변 매장 정보 ");
        //actionBar = getSupportActionBar();
//        actionBar.setDisplayShowCustomEnabled(false);
//        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        actionBar.setCustomView(R.layout.bar_layout);

        header = getLayoutInflater().inflate(R.layout.bar_layout, null, false);
        title_bar = header.findViewById(R.id.custom_bar);
        title_bar.setText("주변 매장 정보");

        listView = (ListView) v.findViewById(R.id.listview);
        adapter = new MyAdapter();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permission_list, 0);
        } else {
            init();
        }
        return v;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int result : grantResults) {
            if (result == PackageManager.PERMISSION_DENIED) {
                return;
            }
        }
        init();
//        MapThread thread = new MapThread();
//        thread.start();
    }

    public void init() {
        FragmentManager fragmentManager = this.getChildFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) fragmentManager.findFragmentById(R.id.map);
        MapReadyCallback callback1 = new MapReadyCallback();
        mapFragment.getMapAsync(callback1);
    }

    class MapThread extends Thread {
        @Override
        public void run() {
            super.run();
            if (isRunning) {
                init();
            }

        }
    }


    // 구글 지도 사용 준비가 완료되면 동작하는 콜백
    class MapReadyCallback implements OnMapReadyCallback {

        @Override
        public void onMapReady(GoogleMap googleMap) {
            map = googleMap;

            // 현재 위치
            getMyLocation();
        }
    }

    // 현재 위치를 측정하는 메서드
    public void getMyLocation() {
        locationManager = (LocationManager) getContext().getSystemService(LOCATION_SERVICE);

        // 권한 확인 작업
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
                return;
            }
        }

        // 이전에 측정했던 값을 가져옴
        Location location1 = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Location location2 = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        if (location1 != null) {
            setMyLocation(location1);
        } else {
            if (location2 != null) {
                setMyLocation(location2);
            }
        }
        // 새롭게 측정함
        GetMyLocationListener listener = new GetMyLocationListener();
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) == true) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10f, listener);
        }
        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) == true) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 10f, listener);
        }
    }

    public void setMyLocation(Location location) {
//        Log.d("test123", "위도: " + location.getLatitude());
//        Log.d("test123", "경도: " + location.getLongitude());
        currentLocation = new Location("current");
        currentLocation.setLatitude(location.getLatitude());
        currentLocation.setLongitude(location.getLongitude());

        // 위도와 경도값을 관리하는 객체
        LatLng position = new LatLng(location.getLatitude(), location.getLongitude());
        CameraUpdate update1 = CameraUpdateFactory.newLatLng(position);
        CameraUpdate update2 = CameraUpdateFactory.zoomTo(15f);
        map.moveCamera(update1);
        map.animateCamera(update2);

        // 권한 확인 작업
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try{
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
                    return;
                }
            }
            catch (Exception e){
                return;
            }

        }
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (requestPermissions(permission_list, PackageManager.PERMISSION_DENIED));
//             return;
//            }
//        }

        // 현재 위치 표시
        map.setMyLocationEnabled(true);

        NetworkThread thread = new NetworkThread(location.getLatitude(), location.getLongitude());
        thread.start();
    }

    // 현재 위치 측정이 성공하면 반응하는 리스너
    class GetMyLocationListener implements LocationListener {
        @Override
        public void onLocationChanged(@NonNull Location location) {
            setMyLocation(location);
            locationManager.removeUpdates(this);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(@NonNull String provider) {

        }

        @Override
        public void onProviderDisabled(@NonNull String provider) {

        }
    }

    // 구글 서버에서 주변 정보를 받아오기 위한 쓰래드
    class NetworkThread extends Thread {
        double lat, lng;

        NetworkThread(double lat, double lng) {
            this.lat = lat;
            this.lng = lng;
        }

        @Override
        public void run() {
            super.run();

            OkHttpClient client = new OkHttpClient();
            Request.Builder builder = new Request.Builder();

            String site = "https://maps.googleapis.com/maps/api/place/nearbysearch/json"
                    + "?key=AIzaSyDJ0mbhei8kzTawEdyKLL1uZLE4QPMCwUs"
                    + "&location=" + lat + "," + lng
                    + "&radius=1000"
                    + "&language=ko"
                    + "&type=cafe";
            //Log.d("test123", "주소 : " + site);
            builder = builder.url(site);
            Request request = builder.build();

            Callback callback1 = new Callback1();
            Call call = client.newCall(request);
            call.enqueue(callback1);

        }
    }

    class Callback1 implements Callback {
        @Override
        public void onFailure(Call call, IOException e) {

        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            try {
                String result = response.body().string();
                //Log.d("test123", result);

                JSONObject obj = new JSONObject(result);

                String status = obj.getString("status");
                if (status.equals("OK")) {
                    JSONArray results = obj.getJSONArray("results");
                    lat_lst.clear();
                    lng_lst.clear();
                    name_lst.clear();
                    vicinity_lst.clear();

                    for (int i = 0; i < results.length(); i++) {
                        JSONObject obj2 = results.getJSONObject(i);
                        JSONObject geometry = obj2.getJSONObject("geometry");
                        JSONObject location = geometry.getJSONObject("location");
                        double lat2 = location.getDouble("lat");
                        double lng2 = location.getDouble("lng");

                        String name = obj2.getString("name");
                        String vicinity = obj2.getString("vicinity");

                        lat_lst.add(lat2);
                        lng_lst.add(lng2);
                        name_lst.add(name);
                        vicinity_lst.add(vicinity);

                        if (i == 15) break;

                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            float distance = 0;
//                            GetDataThread getdatathread;
                            cafeLocation = new Location("cafe");
                            // 지도에 표시되어있는 마커 제거
                            for (Marker marker : marker_lst) {
                                marker.remove();
                            }
                            marker_lst.clear();

                            for (int i = 0; i < lat_lst.size(); i++) {
                                double lat3 = lat_lst.get(i);
                                double lng3 = lng_lst.get(i);
                                String name3 = name_lst.get(i);
                                String vicinity3 = vicinity_lst.get(i);

                                LatLng position = new LatLng(lat3, lng3);
                                MarkerOptions option = new MarkerOptions();
                                option.position(position);

                                option.title(name3);
                                option.snippet(vicinity3);

                                // 마커 이미지 변경
//                                BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.marker);
//                                option.icon(bitmap);

                                Marker marker = map.addMarker(option);
                                marker_lst.add(marker);

                                // 리스트뷰에 아이템 추가
                                cafeLocation = new Location("cafe");
                                cafeLocation.setLatitude(lat3);
                                cafeLocation.setLongitude(lng3);
                                distance = currentLocation.distanceTo(cafeLocation);

                                if (name3.length() < 20) {
                                    // 카페 인자 전달 -> 해당 카페가 위생 등급 정보가 있는지 확인
                                    //try {
                                        adapter.addItem(new MapItem(name3, String.format("%.2f", distance) + " m", R.drawable.green_place));
//                                    } catch (ExecutionException e) {
//                                        e.printStackTrace();
//                                    } catch (InterruptedException e) {
//                                        e.printStackTrace();
//                                    }

                                }
                            }
                            listView.setAdapter(adapter);
//                            for(int i = 0; i < adapter.getCount(); i++)
//                            Log.d("item: ", adapter.getItem(i).toString());
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class MyAdapter extends BaseAdapter implements View.OnClickListener {
        ArrayList<MapItem> items = new ArrayList<MapItem>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(MapItem item) {
            items.add(item);
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            MapItemView mapItemView = null;
            if (convertView == null) {
                mapItemView = new MapItemView(getActivity().getApplicationContext());
            } else {
                mapItemView = (MapItemView) convertView;
            }

            MapItem item = items.get(position);
            mapItemView.setCafeName(item.getCafe_name());
            mapItemView.setDistance(item.getDistance());
            mapItemView.setImg(item.getResId());

            // 리스트뷰 아이템 클릭 처리
            mapItemView.setTag(position);
            mapItemView.setOnClickListener(this);

            return mapItemView;
        }

        @Override
        public void onClick(View v) {
            int pos = (Integer) v.getTag();
            MapItem item = (MapItem) getItem(pos);
            Toast.makeText(getActivity().getApplicationContext(), item.getCafe_name(), Toast.LENGTH_SHORT).show();
            // 아이템 목록 클릭 시


        }
    }

    class OpenAPI extends AsyncTask<Void, Void, String> {
        String url;

        OpenAPI(String url) {
            this.url = url;
        }

        @Override
        protected String doInBackground(Void... voids) {
            String result = "";
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = null;

            try {
                documentBuilder = dbFactory.newDocumentBuilder();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }

            Document doc = null;

            try {
                doc = documentBuilder.parse(url);
            } catch (IOException | SAXException e) {
                e.printStackTrace();
            }

            try {
                doc.getDocumentElement().normalize();

                NodeList nodeList = doc.getElementsByTagName(doc.getDocumentElement().getNodeName());
                //Log.d("len: ", ""+ nodeList.getLength());

                for (int i = 0; i < nodeList.getLength(); i++) {
                    Node node = nodeList.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        // 위생 등급에 따라 이미지 노출
                        if (getTagValue("CODE", element).equals("INFO-000")) {
//                            Log.d("OPEN_API", "이름: " + getTagValue("BSSH_NM", element));
//                            Log.d("OPEN_API", "위생등급: " + getTagValue("HG_ASGN_LV", element));
                            result = getTagValue("HG_ASGN_LV", element);
                        }

                    }
                }
            }
            // 위생등급 데이터가 없는 경우
            catch (Exception e) {
                //Log.d("ds", "null");
            }

            //System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

    String getTagValue(String tag, Element element) {
        NodeList nList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nList.item(0);
        if (nValue == null) {
            return null;
        }
        return nValue.getNodeValue();
    }

    // 인자로 넘어온 카페가 데이터 목록에 있는지 확인
    // 위생 등급에 따라 알맞은 이미지 리턴
    public int setImgColor(String name) throws ExecutionException, InterruptedException {
        String result = "";
        url = "http://openapi.foodsafetykorea.go.kr/api/e740909b02604cfcb677/C004/xml/1/5";
        url += "/UPSO_NM=" + name;

        OpenAPI data = new OpenAPI(url);
        result = data.execute().get();

        if (result.length() == 0) return R.drawable.green_place;
        else if (result.contains("우수") || result.contains("좋음")) return R.drawable.green_place;
        else return R.drawable.red_place;
    }

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        isRunning = false;
//    }
}
