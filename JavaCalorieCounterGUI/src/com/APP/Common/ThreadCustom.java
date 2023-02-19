package com.APP.Common;

public class ThreadCustom implements GlobalConstants{
    public static void main(String[] args) {
        ThreadCustom threadCustom = new ThreadCustom();
        threadCustom.Vital();
    }

    public int Vital() {
        VitalThread vitalThread = new VitalThread();
        vitalThread.start();
        VitalConstants.setVitalValue(vitalThread.getVital());
        return vitalThread.getVital();
    }
    class VitalThread extends Thread{ // 스레드 클래스 작성 : 상속 받아 만듦.
        int vital;
        
        public int getVital() {
            return vital;
        }

        public void setVital(int vital) {
            this.vital = vital;
            VitalConstants.setVitalValue(vital);
        }
    
        public void run() { // 스레드 실행 시작(메인) 매서드 // 오버라이딩
            
            for (int i=0; i<1000; i++) { //i는 횟수임 999번 근데 왜 배열이 안됨.
                int min=70;
                int b=100;
                int max=min+b; //170
                vital = (int)(Math.random()*b)+min;
                
                 
                  if(vital<min || vital>max){
                    continue;
                  }
                   if(Math.abs(vital-100)>20 ||(vital+10<85)||(vital-15>130)){
                    continue;
                   }
            
                
                try {
                    setVital(vital);
                    sleep(1000); // 1000 = 1초
                    // System.out.println(VitalConstants.getVitalValue());
                }
                catch (Exception e) {}
            }
        }
    }

}

