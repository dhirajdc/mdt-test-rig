FPATH="/home/mdt-worker/poc/work"
curl -XPOST -d @${FPATH}/jk_prim_1_1.json http://127.0.0.1:8080/api/notebook/import > ${FPATH}/out.txt
noteid=`cat ${FPATH}/out.txt | cut -d, -f3 | cut -d: -f2 | cut -d} -f1 | cut -d\" -f2`
echo "uov_prim_1_1.sh,$noteid" > ${FPATH}/params.txt
curl -XPOST -d @${FPATH}/jk_test_1_1.json http://127.0.0.1:8080/api/notebook/import > ${FPATH}/out.txt
noteid=`cat ${FPATH}/out.txt | cut -d, -f3 | cut -d: -f2 | cut -d} -f1 | cut -d\" -f2`
echo "uov_test_1_1.sh,$noteid" >> ${FPATH}/params.txt
curl -XPOST -d @${FPATH}/jk_comparator_1_1.json http://127.0.0.1:8080/api/notebook/import > ${FPATH}/out.txt
noteid=`cat ${FPATH}/out.txt | cut -d, -f3 | cut -d: -f2 | cut -d} -f1 | cut -d\" -f2`
echo "comparator_1_1.sh,$noteid" >> ${FPATH}/params.txt
curl -XPOST -d @${FPATH}/jk_prim_1_2.json http://127.0.0.1:8080/api/notebook/import > ${FPATH}/out.txt
noteid=`cat ${FPATH}/out.txt | cut -d, -f3 | cut -d: -f2 | cut -d} -f1 | cut -d\" -f2`
echo "uov_prim_1_2.sh,$noteid" > ${FPATH}/params.txt
curl -XPOST -d @${FPATH}/jk_test_1_2.json http://127.0.0.1:8080/api/notebook/import > ${FPATH}/out.txt
noteid=`cat ${FPATH}/out.txt | cut -d, -f3 | cut -d: -f2 | cut -d} -f1 | cut -d\" -f2`
echo "uov_test_1_2.sh,$noteid" >> ${FPATH}/params.txt
curl -XPOST -d @${FPATH}/jk_comparator_1_2.json http://127.0.0.1:8080/api/notebook/import > ${FPATH}/out.txt
noteid=`cat ${FPATH}/out.txt | cut -d, -f3 | cut -d: -f2 | cut -d} -f1 | cut -d\" -f2`
echo "comparator_1_2.sh,$noteid" >> ${FPATH}/params.txt
