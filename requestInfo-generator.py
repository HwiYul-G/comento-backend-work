import random;
import datetime;

request_codes = ['L', 'O', 'WB', 'DB']
max_user_id = 32 # 현재 모든 인원 수에 따라서 변경

# 시작 날짜를 변경하고 싶다면 변경
start_date = datetime.datetime(2022, 1, 1, 0, 0)
end_data = datetime.datetime.now()

def generate_random_date(start, end):
    return start + (end - start) * random.random()

insert_queries = []

for i in range(1, 3001):
    request_id = i
    request_code = random.choice(request_codes)
    user_id = random.randint(1, max_user_id)
    random_date = generate_random_date(start_date, end_data)
    create_date = random_date.strftime('%y%m%d%H%M')

    insert_queries.append(f"({request_id}, '{request_code}', '{user_id}', '{create_date}')")

# 전체 쿼리 생성
insert_query = f"INSERT INTO statistic9.requestInfo(requestID, requestCode, userID, createDate) VALUES\n" + ",\n".join(insert_queries) + ";"

# 파일로 저장
with open("insert_queries.txt", "w") as file:
    file.write(insert_query)

print("쿼리가 insert_queries.txt 파일에 저장되었습니다.")



