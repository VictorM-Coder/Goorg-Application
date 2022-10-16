import 'swiper/css';

import { A11y, Navigation, Pagination } from 'swiper';
import { Swiper, SwiperProps } from 'swiper/react';

interface SliderProps {
   settings: SwiperProps;
   children: React.ReactNode;
}

export function Slider({ settings, children }: SliderProps){
   return (
      <div>
         <Swiper modules={[Navigation, Pagination, A11y]} { ...settings } className="z-0">
            { children }
         </Swiper>
      </div>
   )
}