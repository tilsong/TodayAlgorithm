package Boj.구현;
import java.util.*;


// 60m (Success)
// 입력: 요금표(fees), 입/출차 기록(records)
// 출력: 자동차별 주차 요금
// 풀이 방향: 주차 관련 데이터를 저장하는 타입과 이를 저장하는 map을 만들고, record 정보에 따라 주차 관련 데이터를 update하도록 함.
//          이후 각 주차 데이터 별 요금을 계산함.
// 시간 복잡도: record 만큼 반복 + 주차 차량 수만큼 반복 => O(N)으로 정리.

class 주차요금계산 {
    static int defaultTime;
    static int defaultFee;
    static int unitTime;
    static int unitFee;

    public int[] solution(int[] fees, String[] records) {
        defaultTime = fees[0];
        defaultFee = fees[1];
        unitTime = fees[2];
        unitFee = fees[3];

        Map<String, Parking> map = new HashMap<>();

        for (String record: records) {
            String[] content = record.split(" ");// 0:출입 시간, 1:차 번호, 2:출입 여부

            if (!map.containsKey(content[1])) { // car가 아직 기록에 없는 경우 -> 새로운 Parking 추가
                Parking parking = new Parking();
                parking.carName = content[1];
                parking.startTime = content[0];
                parking.isOut = false;
                map.put(parking.carName, parking);
            } else {
                Parking parking = map.get(content[1]);

                if (!parking.isOut) { // 이미 차가 있어서 나가는 상황
                    parking.totalTime += calcTime(parking.startTime, content[0]);
                    parking.isOut = true;
                } else { // 새로 차가 들어가는 상황
                    parking.startTime = content[0];
                    parking.isOut = false;
                }
            }
        }

        // 번호 순 정렬
        List<Parking> parkings = new ArrayList<>(map.values());
        Collections.sort(parkings);
        int[] answer = new int[parkings.size()];

        // 아직 isOut이 false인 Parking이 있으면 startTime으로 부터23:59까지의 fee를 계산하기
        for (int i = 0; i < parkings.size(); i++) {
            Parking parking = parkings.get(i);
            if (!parking.isOut) {
                parking.totalTime += calcTime(parking.startTime, "23:59");
            }

            answer[i] = calcFee(parking.totalTime);
        }
        return answer;
    }


    private int calcTime(String startTime, String endTime) {
        String[] start = startTime.split(":");
        int startHour = Integer.parseInt(start[0]);
        int startMin = Integer.parseInt(start[1]);

        String[] end = endTime.split(":");
        int endHour = Integer.parseInt(end[0]);
        int endMin = Integer.parseInt(end[1]);

        int min;

        if (startMin <= endMin) {
            min = (endHour - startHour) * 60;
            min += endMin - startMin;
        } else {
            min = (endHour - startHour - 1) * 60;
            min += endMin + (60 - startMin);
        }

        return min;
    }

    private int calcFee(int min) {
        if (min <= defaultTime) {
            return defaultFee;
        }

        int fee = defaultFee;
        int plusTime = min - defaultTime;

        if (plusTime % unitTime != 0) { // unitTime에 대한 잔여가 있으면 올림 처리
            fee += (plusTime / unitTime + 1) * unitFee;
        } else {
            fee += (plusTime / unitTime) * unitFee;
        }

        return fee;
    }

    static class Parking implements Comparable<Parking> {
        String carName;
        int totalTime;
        String startTime;
        boolean isOut;

        @Override
        public int compareTo(Parking other) {
            int thisCarName = Integer.parseInt(this.carName);
            int otherCarName = Integer.parseInt(other.carName);

            if (thisCarName - otherCarName < 0) {
                return -1;
            }
            return 1;
        }
    }
}