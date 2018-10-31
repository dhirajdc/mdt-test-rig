FPATH="/home/mdt-worker/poc/work"

curl -XPOST -d @${FPATH}/Uov_Comparator_1.json http://127.0.0.1:9010/api/notebook/import > ${FPATH}/out.txt
noteid=`cat ${FPATH}/out.txt | cut -d, -f3 | cut -d: -f2 | cut -d} -f1 | cut -d\" -f2`
echo "Uov_Comparator_1.sh,$noteid" >> ${FPATH}/params.txt


curl -XPOST -d @${FPATH}/Uov_Comparator_2.json http://127.0.0.1:9010/api/notebook/import > ${FPATH}/out.txt
noteid=`cat ${FPATH}/out.txt | cut -d, -f3 | cut -d: -f2 | cut -d} -f1 | cut -d\" -f2`
echo "Uov_Comparator_2.sh,$noteid" >> ${FPATH}/params.txt
