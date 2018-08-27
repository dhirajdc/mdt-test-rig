#fname=`basename $0`
#var=`grep $fname /home/mdt-worker/poc/work/params.txt | cut -d, -f2`
#curl -XPOST http://127.0.0.1:8080/api/notebook/job/${var}

java -cp /home/mdt-worker/poc/work/mdt-poc-1.0-SNAPSHOT-jar-with-dependencies.jar Uov_Test_2 $1
