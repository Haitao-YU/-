package ltd.linqiu.service;

import ltd.linqiu.entity.VolunteerUser;

public interface IVolunteerUserService {
    Integer add(VolunteerUser volunteerUser);

    Integer login(VolunteerUser volunteerUser);
}